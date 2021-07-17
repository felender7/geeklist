package co.za.theappbrewery.phonebooktest

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.theappbrewery.phonebooktest.core.adapter.UserAdapter
import co.za.theappbrewery.phonebooktest.core.model.User
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView =  findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //val users = arrayListOf<User>()
        val users: User = intent.getParcelableArrayListExtra<Parcelable>("EXTRA_PERSON")as User

        //users.add(User(users.name, users.phone, users.roleName, users.profileUrl))

        //val users = intent.getParcelableArrayListExtra<User>("EXTRA_PERSON")as User



        //bind and create view using adapter class
        val adapter = UserAdapter(users)
        recyclerView.adapter = adapter

        // add on click for elements
        if (adapter != null) {
            adapter.onItemClick = { user ->

                val intent = Intent(this, UserDetailsActivity::class.java)
                intent.putExtra("User", user)
                startActivity(intent)
            }
        }

       //Go to create new window

        val btnCreateUser = findViewById<Button>(R.id.btnCreateUser)
        btnCreateUser.setOnClickListener{
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

    }
}


