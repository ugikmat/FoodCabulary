package id.filkom.mat.foodcab

import android.net.Uri
import id.filkom.mat.foodcab.dummy.DummyContent

/**
 * Created by mat on 5/10/18.
 */
interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(item: DummyContent.DummyItem)
    fun onFragmentInteraction(uri: Uri)
}