package co.za.theappbrewery.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvfirstName = findViewById<TextView>(R.id.tvFirstName)
        val tvgender = findViewById<TextView>(R.id.tvGender)
        val tvphone = findViewById<TextView>(R.id.tvPhone)
        val tvage = findViewById<TextView>(R.id.age)
        val userProPic = findViewById<ImageView>(R.id.imProfile)

        val btnButton = findViewById<Button>(R.id.btnBack)


        val firtName = userprofile.firtName
        val gender = userprofile.gender
        val phone = userprofile.phone
        val age = userprofile.age
        tvfirstName.text = "FIRST NAME \n $firtName"
        tvgender.text = "GENDER \n$gender"
        tvphone.text = "PHONE\n$phone"
        tvage.text = "AGE\n$age"

        btnButton.setOnClickListener(){
            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}