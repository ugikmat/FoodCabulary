package id.filkom.mat.foodcab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import id.filkom.mat.foodcab.model.Kategori

/**
 * Created by mat on 5/13/18.
 */
class MyFoodSortRecyclerViewAdapter(private val context: Context?, private val mValues: List<Kategori>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyFoodSortRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_kategori, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]

        holder.mTitle.text = mValues[position].name
        Picasso.with(context).load(mValues[position].image!!)
                .into(holder.mImage)
        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mImage: ImageView
        val mTitle: TextView
        var mItem: Kategori? = null

        init {
            mTitle = mView.findViewById<View>(R.id.menu_name) as TextView
            mImage = mView.findViewById<View>(R.id.menu_image) as ImageView
        }

        override fun toString(): String {
            return super.toString() + " '" + mTitle.text + "'"
        }
    }
}