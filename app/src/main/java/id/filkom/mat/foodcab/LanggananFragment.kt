package id.filkom.mat.foodcab

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

import id.filkom.mat.foodcab.model.FoodList
import id.filkom.mat.foodcab.model.Food
import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_langganan_list.*

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class LanggananFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null

    private var mAuth: FirebaseAuth? = null

    public lateinit var adapter:RecyclerView.Adapter<MyFoodRecyclerViewAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance();
        adapter = MyFoodRecyclerViewAdapter("fav",activity?.baseContext,FoodList.ITEMS_FAV, mListener)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_langganan_list, container, false)
        Log.d("INFOOOOOOOOOOOOOOOOOOO", FoodList.ITEMS_FAV.toString())
        Log.d("INFOOOOOOOOOOOOOOOOOOO", FoodList.ITEMS.toString())
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view as RecyclerView
            if (mColumnCount <= 1) {
                recyclerView.layoutManager = LinearLayoutManager(context)
            } else {
                recyclerView.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            recyclerView.adapter = adapter
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    companion object {
        val TAG: String = LanggananFragment::class.java.simpleName

        fun newInstance() = LanggananFragment()
    }
}
