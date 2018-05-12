package id.filkom.mat.foodcab

import android.view.View

/**
 * Created by mat on 5/11/18.
 */
interface ItemClickListener {

    fun onClick(view: View, position: Int, isLong: Boolean)
}
