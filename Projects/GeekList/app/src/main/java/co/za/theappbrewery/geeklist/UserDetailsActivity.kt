package co.za.theappbrewery.geeklist

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import co.za.theappbrewery.geeklist.core.model.User
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class UserDetailsActivity : AppCompatActivity() {

    private val REQUEST_CODE = 100

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GeekList)
        setContentView(R.layout.activity_user_details)

        //actionbar
        val actionbar = supportActionBar

        //set actionbar title
        "<Geek/>".also { actionbar!!.title = it }

        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        // write permission to access the storage
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)

        // this is the Main screen  whose screenshot
        // we will take in this article
        // get the view using fin view bt id
        val mainScreen = findViewById<LinearLayout>(R.id.mainScreen)


        // on click of this button it will capture
        // screenshot and save into gallery
        val btnScreenShot = findViewById<ImageButton>(R.id.btnScreenCapture)

        btnScreenShot.setOnClickListener{

            // get the bitmap of the view using
            // getScreenShotFromView method it is
            // implemented below

            val bitmap = getScreenShotFromView(mainScreen)

            // if bitmap is not null then
            // save it to gallery

            if (bitmap != null){
                saveMediaToStorage(bitmap)
            }
        }

        //Get details
        val userName = findViewById<TextView>(R.id.userName)
        val userPhone = findViewById<TextView>(R.id.tvPhone)
        val userRole = findViewById<TextView>(R.id.userRole)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val imTakePicture = findViewById<ImageButton>(R.id.imTakePicture)
        val btnCall = findViewById<ImageView>(R.id.btnCall)


        val user = intent.getParcelableExtra<User>("User")

        userName.text = user?.name
        userPhone.text = user?.phone
        userRole.text = user?.roleName
        Picasso.get().load(user?.profileUrl).into(imageView)


        // Make a call
       btnCall.setOnClickListener{
            val telephoneSchema = "tel:"
            val phoneNumber = user?.phone
            val phoneCallUri = Uri.parse(telephoneSchema + phoneNumber)
            val phoneCallIntent = Intent(Intent.ACTION_DIAL).also{ it.data = phoneCallUri }
            startActivity(phoneCallIntent)
       }

        // take picture
        imTakePicture.setOnClickListener(){
            this.capturePhoto()
        }

        //Share button
        val btnShare = findViewById<ImageButton>(R.id.btnShare)

        btnShare.setOnClickListener {
            if (user != null) this.shareProfile(user)
        }

    }

    //function to share user profile name
    private fun shareProfile (user: User) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            val name = user.name
            val phone = user.phone
            val shareDetails ="$name, $phone"
            putExtra(Intent.EXTRA_TEXT, shareDetails)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }



    //Capture Photo
    private fun capturePhoto(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent,REQUEST_CODE)
    }

    private fun getScreenShotFromView(v: View): Bitmap? {
        // create a bitmap object
        var screenshot: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot because:" + e.message)
        }
        // return the bitmap
        return screenshot
    }


    // this method saves the image to gallery
    private fun saveMediaToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this , "Captured View and saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }


    //Display image after Capture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}