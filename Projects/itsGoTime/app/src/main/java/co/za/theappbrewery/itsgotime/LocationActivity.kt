package co.za.theappbrewery.itsgotime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private var googleMaps : GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMaps = googleMaps

        // Add a marker in Sydney and move the camera

        val polokwane = LatLng(-23.9, 29.4)
        googleMaps?.addMarker(
            MarkerOptions()
            .position(polokwane)
            .title("Marker in Polokwane"))
        googleMaps?.moveCamera(CameraUpdateFactory.newLatLng(polokwane))
    }
}