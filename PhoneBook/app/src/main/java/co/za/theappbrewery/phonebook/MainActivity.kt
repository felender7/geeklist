package co.za.theappbrewery.phonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get objects

        var tvName = findViewById<TextView>(R.id.tvName)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val btnClickMe = findViewById<Button>(R.id.btnClickMe)

         val name = userprofile.firtName
        tvName.text = "Hi, $name"
        tvAge.text = userprofile.age

        btnClickMe.setOnClickListener(){
        val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)

        }

    }
}