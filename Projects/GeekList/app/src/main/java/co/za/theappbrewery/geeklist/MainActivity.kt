package co.za.theappbrewery.geeklist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.theappbrewery.geeklist.core.adapter.UserAdapter
import co.za.theappbrewery.geeklist.core.model.User
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GeekList)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.menu.getItem(2).isEnabled = false

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this) {
            loadAd()
        }



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

        //Get data from addGeek intent
        val intent = intent

        val name = intent.getStringExtra("Name").toString()
        val phone = intent?.getStringExtra("Phone").toString()
        val role = intent?.getStringExtra("Role").toString()
        var profileUrl = intent.getStringExtra("ProfileUrl").toString()





        // add elements to array
        users.add(User("Mukondleteri Dumela", "085 9620 766", "Software Developer","https://www.xitsonga.org/assets/images/Mukondleteri.jpg"))
        users.add(User("Charlotte Wue", "0842338499", "Telecommunications","https://www.geekulcha.dev/img/crew/charlotte.jpg"))
        users.add(User("Tiyani Nghonyama", "083 726 4104", "CEO","https://www.geekulcha.dev/img/crew/tiyani.png"))
        users.add(User("Tlangelani Hlungwani", "085 511 6053", "Software & IT infrastructure Engineer","https://pbs.twimg.com/profile_images/1121752940535078912/cZ_zaeiP_400x400.jpg"))
        users.add(User("Keabetswe Chauke", "082 713 5335", "Project Coordination","https://www.geekulcha.dev/img/crew/kea.png"))
        if (profileUrl.isNullOrEmpty()){
            profileUrl = "https://via.placeholder.com/310/f7f7f7/808080/?text=Geeklist"
            users.add(User(name, phone, role, profileUrl))
        }



        // create an assign adapter
        val adapter = UserAdapter(users)
        recyclerView.adapter = adapter


        // add on click for elements
        adapter.onItemClick = { user ->



            val intent = Intent(this, UserDetailsActivity::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
            showInterstitial()
        }


        //Get btn Add
        val btnFabAdd = findViewById<FloatingActionButton>(R.id.btnFabAdd)
         btnFabAdd.setOnClickListener {

             Intent(this, AddGeekActivity::class.java).also {
                 startActivity(it)
                 showInterstitial()
             }
         }
    }
    private fun loadAd() {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            this, "ca-app-pub-3940256099942544/1033173712", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    mInterstitialAd = null

                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    Toast.makeText(
                        this@MainActivity,
                        "onAdFailedToLoad() with error $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    //Toast.makeText(this@MainActivity, "onAdLoaded()", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }



    // Show the ad if it's ready. Otherwise toast and restart the game.
    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                    loadAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d(TAG, "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    // Called when ad is dismissed.

                }
            }
            mInterstitialAd?.show(this)
        } else {
            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
            }

        }
}
