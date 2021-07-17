package co.za.theappbrewery.phonebooktest

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView

class TakePictureActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)

        val btnCapture = findViewById<ImageButton>(R.id.imShootPicture)
        imageView = findViewById<ImageView>(R.id.imageView2)
        btnCapture.setOnClickListener{
            this.capturePhoto()
        }

    }

    // Function to capture picture
    private fun capturePhoto(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,REQUEST_CODE)
    }



    //Display image after Capture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

}