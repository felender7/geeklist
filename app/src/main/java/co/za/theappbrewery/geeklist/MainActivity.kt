package co.za.theappbrewery.geeklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.theappbrewery.geeklist.core.adapter.UserAdapter
import co.za.theappbrewery.geeklist.core.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GeekList)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false


        //actionbar
        val actionbar = supportActionBar

        //set actionbar title
        "<Geeklist/>".also { actionbar!!.title = it }

        //set back button
        if (actionbar != null) actionbar.setHomeAsUpIndicator(R.drawable.ic_dashboard) else throw NullPointerException("Expression 'actionbar' must not be null");// set drawable icon
        actionbar.setDisplayHomeAsUpEnabled(true)


        // link recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // create a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // create array or list adapter
        val users = ArrayList<User>()







        // add elements to array
        users.add(User("Mukondleteri Dumela", "085 9620 766", "Software Developer","https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User("Charlotte Wue", "0842338499", "Telecommunications","https://www.geekulcha.dev/img/crew/charlotte.jpg"))
        users.add(User("Tiyani Nghonyama", "083 726 4104", "CEO","https://www.geekulcha.dev/img/crew/tiyani.png"))
        users.add(User("Tlangelani Hlungwani", "085 511 6053", "Software & IT infrastructure Engineer","https://pbs.twimg.com/profile_images/1121752940535078912/cZ_zaeiP_400x400.jpg"))
        users.add(User("Keabetswe Chauke", "082 713 5335", "Project Coordination","https://www.geekulcha.dev/img/crew/kea.png"))

        // create an assign adapter
        val adapter = UserAdapter(users)
        recyclerView.adapter = adapter

        // add on click for elements
        adapter.onItemClick = { user ->

            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }

        //Get btn Add

        val btnFabAdd = findViewById<FloatingActionButton>(R.id.btnFabAdd)
         btnFabAdd.setOnClickListener {
             Intent(this , AddGeekActivity::class.java).also {
                 startActivity(it)
             }
         }
    }
}
