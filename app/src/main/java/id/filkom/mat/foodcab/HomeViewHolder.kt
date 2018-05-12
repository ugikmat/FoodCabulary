package id.filkom.mat.foodcab

import android.animation.ValueAnimator
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*

/**
 * Created by mat on 5/11/18.
 */

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var txtMenuName: TextView
    var imageView: ImageView

    internal var minHeight: Int = 0


    internal var cardViewList: CardView

    private var itemClickListener: ItemClickListener? = null

    init {
        txtMenuName = itemView.findViewById<View>(R.id.menu_name) as TextView
        imageView = itemView.findViewById<View>(R.id.menu_image) as ImageView

        itemView.setOnClickListener(this)

        //remove if error
        cardViewList = itemView.findViewById<View>(R.id.cardViewList) as CardView

        cardViewList.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {

            override fun onPreDraw(): Boolean {
                cardViewList.viewTreeObserver.removeOnPreDrawListener(this)
                minHeight = cardViewList.height
                val layoutParams = cardViewList.layoutParams
                layoutParams.height = minHeight
                cardViewList.layoutParams = layoutParams

                return true
            }
        })

        itemClickListener = object : ItemClickListener {
            override fun onClick(view: View, position: Int, isLong: Boolean) {
                Toast.makeText(view.context,position.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View) {

        itemClickListener!!.onClick(v, adapterPosition, false)

    }
}