package id.filkom.mat.foodcab

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.extension.enableShiftMode
import id.filkom.mat.foodcab.model.Food
import android.view.ViewGroup
import android.widget.Button
import com.crashlytics.android.Crashlytics




class HomeActivity : AppCompatActivity(), OnListFragmentInteractionListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private var mAuth: FirebaseAuth? = null

    val database = FirebaseDatabase.getInstance()
    val myRef = database.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initBottomNavigation()

        mAuth = FirebaseAuth.getInstance();

        fab_add.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }

        var fragment = HomeFragment()

        addFragment(fragment,R.id.fragment_container)

    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction)
    {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment)
        }
    }

    override fun onBackPressed() {

        val count = fragmentManager.backStackEntryCount

        Toast.makeText(this,count.toString(),Toast.LENGTH_SHORT).show()
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            fragmentManager.popBackStack()
        }

    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{
            replace(frameId, fragment) }
    }


    private fun initBottomNavigation() {
        bottom_navigation.enableShiftMode()
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    fun initDatabase(){
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Beranda" -> replaceFragment(HomeFragment(),R.id.fragment_container)
            "Kategori" -> replaceFragment(HomeFragment(),R.id.fragment_container)
            "Langgangan" -> replaceFragment(HomeFragment(),R.id.fragment_container)
            "Profile" -> replaceFragment(ProfileFragment(),R.id.fragment_container)
        }
        return true
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {

    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()

    }
}
