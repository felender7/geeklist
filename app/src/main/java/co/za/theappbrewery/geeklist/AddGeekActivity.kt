package co.za.theappbrewery.geeklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import co.za.theappbrewery.geeklist.core.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout

class AddGeekActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTheme(R.style.Theme_GeekList)
        setContentView(R.layout.activity_add_geek)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        //actionbar
        val actionbar = supportActionBar

        //set actionbar title
        "<Add Geek/>".also { actionbar!!.title = it }

        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)



        val etName = findViewById<TextInputLayout>(R.id.etName)
        val etPhone = findViewById<TextInputLayout>(R.id.etPhone)
        val etRole = findViewById<TextInputLayout>(R.id.etRole)
        val etProfileUrl = findViewById<TextInputLayout>(R.id.etProfileUrl)

        val btnApply = findViewById<Button>(R.id.btnApply)

        btnApply.setOnClickListener{
            val name = etName.editText?.text.toString()
            val phone = etPhone.editText?.text.toString()
            val role = etRole.editText?.text.toString()
            val profileUrl = etProfileUrl.editText?.text.toString()
            val user = User(
                name,
                phone,
                role,
                profileUrl
            )

            Intent(this, MainActivity::class.java).also {
                it.putExtra("EXTRA_PERSON", user)
                startActivity(it)

            }

        }


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}