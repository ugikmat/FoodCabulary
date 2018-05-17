package id.filkom.mat.foodcab

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import id.filkom.mat.foodcab.model.Food
import kotlinx.android.synthetic.main.activity_add.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import com.google.firebase.storage.StorageReference
import android.support.annotation.NonNull
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.UploadTask
import com.google.android.gms.tasks.OnSuccessListener






class AddActivity : AppCompatActivity(), View.OnClickListener {


    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null

    var saveUri: Uri? = null
    private lateinit var newFood:Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        newFood = Food.create()

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("food")
        choose_image_button.setOnClickListener{pickImage()}

        add_food_button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        newFood.name = name.text.toString()
        newFood.location = location.text.toString()

        var id: Int = radio_group.checkedRadioButtonId

        if (id!=-1){ // If any radio button checked from radio group
            // Get the instance of radio button using id
            val radio:RadioButton = findViewById(id)
            newFood.category = radio.text.toString()
        }else{
            newFood.category = "Lainnya"
            // If no radio button checked in this radio group
        }
        newFood.phone = phone.text.toString()
        newFood.seller = seller.text.toString()

        if(saveUri!=null){
            val imageName:String = UUID.randomUUID().toString()
            val imageFolder:StorageReference? = mStorageRef?.child("image/" + imageName)

            imageFolder?.putFile(saveUri!!)
                    ?.addOnSuccessListener({ taskSnapshot ->
                        // Get a URL to the uploaded content
                        val downloadUrl = taskSnapshot.downloadUrl
                        imageFolder?.downloadUrl.addOnSuccessListener {
                            newFood.image=it.toString()
                            newFood.id=mDatabaseRef?.push()?.key
                            mDatabaseRef?.push()?.setValue(newFood)
                                    ?.addOnSuccessListener {
                                        Toast.makeText(this,"Succes",Toast.LENGTH_SHORT).show()
                                        onBackPressed()
                                    }
                                    ?.addOnFailureListener{
                                        Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                                    }

                        }

                    })
                    ?.addOnFailureListener(OnFailureListener {
                        // Handle unsuccessful uploads
                        // ...
                    })
                    ?.addOnProgressListener {

                    }

        }


    }

    fun uploadImage(){
        if(saveUri!=null){
            val imageName:String = UUID.randomUUID().toString()
            val imageFolder:StorageReference? = mStorageRef?.child("image/" + imageName)

            imageFolder?.putFile(saveUri!!)
                    ?.addOnSuccessListener({ taskSnapshot ->
                        // Get a URL to the uploaded content
                        val downloadUrl = taskSnapshot.downloadUrl
                        imageFolder?.downloadUrl.addOnSuccessListener {
                            newFood.image=it.toString()

                        }

                    })
                    ?.addOnFailureListener(OnFailureListener {

                    })
                    ?.addOnProgressListener {

                    }

        }
    }


    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)

        }
    }
    fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }
    fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_REQUEST)
        }
    }

    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
        private val PICK_IMAGE_REQUEST = 71
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST
                && resultCode == Activity.RESULT_OK
                && data !=null && data.data != null){
            saveUri = data.data
            Toast.makeText(this,"Foto Dipilih", Toast.LENGTH_SHORT).show()
            choose_image_button.text = "Change Image"
        }
    }
}