package co.za.theappbrewery.phonebooktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import co.za.theappbrewery.phonebooktest.core.model.User
import com.google.android.material.textfield.TextInputLayout

class CreateUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

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
}