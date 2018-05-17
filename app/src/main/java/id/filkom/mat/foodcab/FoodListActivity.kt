package id.filkom.mat.foodcab

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.model.Food
import id.filkom.mat.foodcab.model.FoodList
import kotlinx.android.synthetic.main.activity_food_list.*
import kotlinx.android.synthetic.main.food_list_content.view.*

import kotlinx.android.synthetic.main.food_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [FoodDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class FoodListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mListener: OnListFragmentInteractionListener? = null
    private var mTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (food_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        setupRecyclerView(food_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
//        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, mTwoPane)
        recyclerView.adapter = SimpleItemRecyclerViewAdapter("kategori",this, FoodList.ITEMS_KATEGORI)
    }

    class SimpleItemRecyclerViewAdapter(private val who:String?,
                                        private val context: Context?,
                                        private val mValues: List<Food>)
        : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>()  {

//        private val mOnClickListener: View.OnClickListener
//
//        init {
//            mOnClickListener = View.OnClickListener { v ->
//                val item = v.tag as Food
//                val intent = Intent(context, FoodDetailActivity::class.java).apply {
//                    putExtra(FoodDetailFragment.ARG_ITEM_ID, index)
//                    putExtra("who", who)
//                }
//                context?.startActivity(intent)
//            }
//        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.menu_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mItem = mValues[position]

            holder.mTitle.text = mValues[position].name
            Picasso.with(context).load(mValues[position].image)
                    .into(holder.mImage)

            holder.itemView.setOnClickListener {
                val intent = Intent(context, FoodDetailActivity::class.java).apply {
                    putExtra(FoodDetailFragment.ARG_ITEM_ID, position)
                    putExtra("who", who)
                }
                context?.startActivity(intent)
            }
//            with(holder.itemView) {
//                tag = item
//                setOnClickListener(mOnClickListener)
//            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mImage: ImageView
            val mTitle: TextView
            var mItem: Food? = null

            init {
                mTitle = mView.findViewById<View>(R.id.menu_name) as TextView
                mImage = mView.findViewById<View>(R.id.menu_image) as ImageView
            }
        }
    }
}
