package co.za.theappbrewery.geeklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {

    //Initiate SPLASH TIME
    private  var SPLASH_TIME: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

     // Delay Function
     Handler(Looper.getMainLooper()).postDelayed({
         startActivity(Intent(this, MainActivity::class.java))
         finish()
     }, SPLASH_TIME)

    }

}
