package id.filkom.mat.foodcab

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.filkom.mat.foodcab.model.FoodList

import kotlinx.android.synthetic.main.fragment_kategori_list.*

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
class KategoriFragment : Fragment() {
    private var mColumnCount = 3
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_kategori_list, container, false)

//        // Set the adapter
//        if (view is RecyclerView) {
//            val context = view.getContext()
//            val recyclerView = view as RecyclerView
//            if (mColumnCount <= 1) {
//                recyclerView.layoutManager = LinearLayoutManager(context)
//            } else {
//                recyclerView.layoutManager = GridLayoutManager(context, mColumnCount)
//            }
//            recyclerView.adapter = MykategoriRecyclerViewAdapter(activity?.baseContext,FoodList.KATEGORI_ITEMS, mListener)
//        }
//
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_top.layoutManager = LinearLayoutManager(recycler_top.context)
        recycler_top.adapter = MykategoriRecyclerViewAdapter(activity?.baseContext,FoodList.KATEGORI_ITEMS_TOP, mListener)

        recycler_kat.layoutManager = GridLayoutManager(recycler_kat.context,mColumnCount)
        recycler_kat.adapter = MyFoodSortRecyclerViewAdapter(activity?.baseContext,FoodList.KATEGORI_ITEMS, mListener)

        recycler_list.layoutManager = LinearLayoutManager(recycler_list.context)
        recycler_list.adapter = MykategoriRecyclerViewAdapter(activity?.baseContext,FoodList.KATEGORI_ITEMS_TOP, mListener)
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

        // TODO: Customize parameter argument names
        private val ARG_COLUMN_COUNT = "column-count"

        val TAG: String =KategoriFragment::class.java.simpleName

        // TODO: Customize parameter initialization
        fun newInstance()= KategoriFragment()
    }
}
