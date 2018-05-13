package id.filkom.mat.foodcab

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    private var mListener: OnListFragmentInteractionListener? = null

    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        mAuth = FirebaseAuth.getInstance();
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_email.text = mAuth?.currentUser?.email
        text_uid.text = mAuth?.currentUser?.uid
        btn_logout.setOnClickListener {
            mAuth?.signOut()
            activity?.startActivity(Intent(activity,LoginActivity::class.java))
            activity?.finish()
        }
    }

    companion object {

        val TAG: String = ProfileFragment::class.java.simpleName

        fun newInstance()= ProfileFragment()
    }
}// Required empty public constructor
