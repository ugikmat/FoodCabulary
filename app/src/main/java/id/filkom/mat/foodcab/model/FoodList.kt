package id.filkom.mat.foodcab.model

import android.widget.Toast
import id.filkom.mat.foodcab.FoodDetailFragment
import id.filkom.mat.foodcab.R
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by mat on 5/11/18.
 */
object FoodList {
    val ITEMS:MutableList<Food> = ArrayList<Food>()
    val ITEM_MAP: MutableMap<Int, Food> = HashMap<Int, Food>()
    val ITEMS_FAV:MutableList<Food> = ArrayList<Food>()
    val ITEM_FAV_MAP: MutableMap<Int, Food> = HashMap<Int, Food>()
    val ITEMS_KATEGORI:MutableList<Food> = ArrayList<Food>()
    val ITEM_KATEGORI_MAP: MutableMap<Int, Food> = HashMap<Int, Food>()
    val KATEGORI_ITEMS:MutableList<Kategori> = ArrayList<Kategori>()
    val KATEGORI_ITEMS_TOP:MutableList<Kategori> = ArrayList<Kategori>()

    val kat = arrayListOf<String>("Soto","Bakso","Lainnya","Mie","Pecel","Ayam")
    val im:IntArray = intArrayOf(R.drawable.soto,R.drawable.bakso,R.drawable.other,R.drawable.mie,R.drawable.pecel,R.drawable.ayam)

    init {

        for(i in 0..5){
            KATEGORI_ITEMS.add(Kategori.create())
            KATEGORI_ITEMS.get(i).name=kat[i]
            KATEGORI_ITEMS.get(i).image= im[i]
        }
        KATEGORI_ITEMS_TOP.addAll(KATEGORI_ITEMS.subList(0,3))
    }

    fun contain(index:Int):Boolean{
        if(!Users.user?.fav?.isEmpty()!!){
            Users.user!!.fav.forEach {
                if(it.id==FoodList.ITEM_MAP[index]?.id){
                    return true
                }
            }
        }
        return false
    }
}