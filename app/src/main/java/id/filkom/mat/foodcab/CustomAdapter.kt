package id.filkom.mat.foodcab

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.filkom.mat.foodcab.model.Dummy
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * Created by mat on 5/9/18.
 */
class CustomAdapter (val dummyList:List<Dummy>,private val listener:Listener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return dummyList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(dummyList.get(position),listener)
    }

    interface Listener{
        fun onItemClick(dummy: Dummy)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(dummy: Dummy,listener:Listener){

            itemView.text_dummy.text = dummy.id.toString()
            itemView.setOnClickListener{ listener.onItemClick(dummy)}

        }
    }
}