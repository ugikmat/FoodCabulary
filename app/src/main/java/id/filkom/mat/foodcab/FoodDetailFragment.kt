package id.filkom.mat.foodcab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.model.Food
import kotlinx.android.synthetic.main.activity_food_detail.*
import kotlinx.android.synthetic.main.food_detail.view.*

import id.filkom.mat.foodcab.model.FoodList
import kotlinx.android.synthetic.main.food_detail.*

/**
 * A fragment representing a single Food detail screen.
 * This fragment is either contained in a [FoodListActivity]
 * in two-pane mode (on tablets) or a [FoodDetailActivity]
 * on handsets.
 */
class FoodDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var mItem: Food? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                when(it.getString("who")){
                    "home" -> mItem = FoodList.ITEM_MAP[it.getInt(ARG_ITEM_ID)]
                    "fav" ->mItem = FoodList.ITEMS_FAV[it.getInt(ARG_ITEM_ID)]
                    "kategori" -> mItem = FoodList.ITEMS_KATEGORI[it.getInt(ARG_ITEM_ID)]
                }
                activity?.toolbar_layout?.title = mItem?.name
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.food_detail, container, false)

        // Show the dummy content as text in a TextView.
        mItem?.let {
            rootView.food_detail.text = it.location
            rootView.food_seller.text = it.seller
            rootView.food_phone.text = it.phone


        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
