package id.filkom.mat.foodcab

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import id.filkom.mat.foodcab.model.Dummy
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), CustomAdapter.Listener {

    override fun onItemClick(dummy: Dummy) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mAuth = FirebaseAuth.getInstance();

        btn_sign_out.setOnClickListener {
            mAuth!!.signOut()
            startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth?.getCurrentUser()

        text_uid.text = currentUser!!.uid
        text_name.text = currentUser?.displayName
        text_email.text = currentUser!!.email
    }
}
