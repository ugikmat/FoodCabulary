package id.filkom.mat.foodcab

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import id.filkom.mat.foodcab.dummy.DummyContent
import id.filkom.mat.foodcab.model.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_food_list.*
import java.text.NumberFormat
import java.util.*


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
class HomeFragment : Fragment() {
    private var mColumnCount = 1
    private var mListener: OnListFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("create","OnCreate")
        FoodList.ITEMS.clear()
        loadMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            view.adapter = MyFoodRecyclerViewAdapter(activity?.baseContext,FoodList.ITEMS, mListener)
        }

        return view
    }

    private fun loadMenu() {
        var database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("food")

        // Read from the database
        myRef.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                FoodList.ITEMS.add(p0?.getValue(Food::class.java)!!)
                FoodList.ITEM_MAP.put(FoodList.ITEMS.size-1,p0?.getValue(Food::class.java)!!)
                list.adapter.notifyItemInserted(FoodList.ITEMS.size-1)

            }

            override fun onChildRemoved(p0: DataSnapshot?) {

            }

        })
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

    companion object {

        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }
}

