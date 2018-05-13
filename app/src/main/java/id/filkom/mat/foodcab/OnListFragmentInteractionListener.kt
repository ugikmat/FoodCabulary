package id.filkom.mat.foodcab

import android.net.Uri
import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.model.Food
import id.filkom.mat.foodcab.model.Kategori

/**
 * Created by mat on 5/10/18.
 */
interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(item: Food)
    fun onFragmentInteraction(uri: Uri)
    fun onListFragmentInteraction(item: DummyContent.DummyItem)
    fun onListFragmentInteraction(item: Kategori)
}