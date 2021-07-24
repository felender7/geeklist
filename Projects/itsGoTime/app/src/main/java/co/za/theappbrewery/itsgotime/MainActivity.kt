package co.za.theappbrewery.itsgotime

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds



class MainActivity : AppCompatActivity(){

    private var AdView : AdView? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showMap = findViewById<Button>(R.id.btnShowMap)
        AdView = findViewById<AdView>(R.id.adView)

        //initialize mobile adds
        MobileAds.initialize(this) {}

        //Show add
        val adRequest = AdRequest.Builder().build()

        AdView?.loadAd(adRequest)


        showMap.setOnClickListener{
            Intent(this, LocationActivity::class.java).also {
                startActivity(it)
            }
        }
    }











}