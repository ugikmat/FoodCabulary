package id.filkom.mat.foodcab

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*
import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.extension.*
import id.filkom.mat.foodcab.helper.*
import android.view.MenuInflater
import android.support.v4.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import android.app.SearchManager
import android.content.Context
import android.support.v7.widget.Toolbar
import android.widget.SearchView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.filkom.mat.foodcab.model.*


class HomeActivity : AppCompatActivity(), OnListFragmentInteractionListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private var mAuth: FirebaseAuth? = null
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("users")

    override fun onListFragmentInteraction(item: Kategori) {

    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {

    }

    private val KEY_POSITION = "keyPosition"

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSaveInstanceState(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mAuth = FirebaseAuth.getInstance();

        initBottomNavigation()

        fab_add.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }

        initBottomNavigation()
        initFragment(savedInstanceState)
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)
            navPosition = findNavigationPositionById(id)
        }
    }

    private fun initBottomNavigation() {
        bottom_navigation.enableShiftMode()
        bottom_navigation.active(navPosition.position)   // Extension function
        bottom_navigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        // Store the current navigation position.
        outState?.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navPosition = findNavigationPositionById(item.itemId)
        return switchFragment(navPosition)
    }

    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        val fragment = supportFragmentManager.findFragment(navPosition)
        if (fragment.isAdded) return false
        detachFragment()
        attachFragment(fragment, navPosition.getTag())
        supportFragmentManager.executePendingTransactions()
        return true
    }

    private fun detachFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.also {
            supportFragmentManager.beginTransaction().detach(it).commit()
        }
    }

    private fun attachFragment(fragment: Fragment, tag: String) {
        if (fragment.isDetached) {
            supportFragmentManager.beginTransaction().attach(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
        // Set a transition animation for this transaction.
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }


    override fun onListFragmentInteraction(index: Int) {


        val intent = Intent(this, FoodDetailActivity::class.java).apply {
            putExtra(FoodDetailFragment.ARG_ITEM_ID, index)
        }
        startActivity(intent)

//        if(!Users.user?.fav?.isEmpty()!!){
//            Users.user.fav.forEach {
//                if(it.name?.equals(item?.name,true)!!
//                        &&it.seller?.equals(item?.seller,true)!!){
//                    Toast.makeText(this,"Already Added",Toast.LENGTH_SHORT ).show()
//                    return
//                }
//            }
//        }
//        Users.user.fav.add(item)
//        myRef.child(mAuth?.uid).setValue(Users.user)
//        Toast.makeText(this,"Added to Fav",Toast.LENGTH_SHORT ).show()
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))

        return true
    }
}
