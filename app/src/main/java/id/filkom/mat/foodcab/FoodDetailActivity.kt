package id.filkom.mat.foodcab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import id.filkom.mat.foodcab.model.FoodList
import kotlinx.android.synthetic.main.activity_food_detail.*
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.http.SslCertificate.saveState
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.filkom.mat.foodcab.model.Food
import id.filkom.mat.foodcab.model.Users


/**
 * An activity representing a single Food detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [FoodListActivity].
 */
class FoodDetailActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("users")

    var detail: Food? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        setSupportActionBar(detail_toolbar)
        mAuth = FirebaseAuth.getInstance()
        if(intent.getStringExtra("who").equals("home",true)){
            detail = FoodList.ITEM_MAP[intent.getIntExtra(FoodDetailFragment.ARG_ITEM_ID,0)]!!
        }else if(intent.getStringExtra("who").equals("fav",true)){
            detail = FoodList.ITEMS_FAV[intent.getIntExtra(FoodDetailFragment.ARG_ITEM_ID,0)]!!
        }else if(intent.getStringExtra("who").equals("kategori",true)){
            detail = FoodList.ITEMS_KATEGORI[intent.getIntExtra(FoodDetailFragment.ARG_ITEM_ID,0)]!!
        }
        Picasso.with(this).load(detail?.image)
                .into(menu_image)

        fab.setOnClickListener {
            var isFavourite = readState()

            if (isFavourite) {
                isFavourite = false
                saveState(isFavourite)

            } else {
                isFavourite = true
                saveState(isFavourite)

            }
            saveFavorite()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = FoodDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(FoodDetailFragment.ARG_ITEM_ID,
                            intent.getIntExtra(FoodDetailFragment.ARG_ITEM_ID,0))
                    putString("who",
                            intent.getStringExtra("who"))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.food_detail_container, fragment)
                    .commit()
        }
        loadFavorite()
    }

    private fun saveState(isFavourite: Boolean) {
        val aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE)
        val aSharedPreferencesEdit = aSharedPreferences
                .edit()
        aSharedPreferencesEdit.putBoolean("State", isFavourite)
        aSharedPreferencesEdit.commit()
        if(isFavourite)fab.setImageResource(R.drawable.ic_favorite_red_300_24dp)
        else fab.setImageResource(R.drawable.ic_favorite_white_24dp)
    }

    private fun readState(): Boolean {
        val aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE)
        if(!aSharedPreferences.contains("State")){

        }

        return aSharedPreferences.getBoolean("State", true)
    }

    private fun loadFavorite(){
        if(!Users.user?.fav?.isEmpty()!!){
            Users.user!!.fav.forEach {
                if(it.id==detail?.id){
                    saveState(true)
                    return
                }
            }
        }
        saveState(false)
    }

    private fun saveFavorite(){
        if(!Users.user?.fav?.isEmpty()!!){
            Users.user!!.fav.forEach {
                if(it.id==detail?.id){
                    Users.user!!.fav.remove(it)
                    FoodList.ITEMS_FAV.remove(detail)
                    myRef.child(mAuth?.uid).setValue(Users.user).addOnCompleteListener {
                        if(it.isSuccessful)Toast.makeText(this,"SUCCESS",Toast.LENGTH_SHORT).show()
                        else if(it.isSuccessful)Toast.makeText(this,"FAILED",Toast.LENGTH_SHORT).show()
                    }
                    Toast.makeText(this,"Removed from Fav",Toast.LENGTH_SHORT ).show()
                    return
                }
            }
        }
        Users.user!!.fav.add(detail!!)
        myRef.child(mAuth?.uid).setValue(Users.user).addOnCompleteListener {
            if(it.isSuccessful)Toast.makeText(this,"SUCCESS",Toast.LENGTH_SHORT).show()
            else if(it.isSuccessful)Toast.makeText(this,"FAILED",Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this,"Added to Fav",Toast.LENGTH_SHORT ).show()
    }

//    override fun onOptionsItemSelected(item: MenuItem) =
//            when (item.itemId) {
//                android.R.id.home -> {
//                    // This ID represents the Home or Up button. In the case of this
//                    // activity, the Up button is shown. For
//                    // more details, see the Navigation pattern on Android Design:
//                    //
//                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back
//
//                    navigateUpTo(Intent(this, FoodListActivity::class.java))
//                    true
//                }
//                else -> super.onOptionsItemSelected(item)
//            }
}
