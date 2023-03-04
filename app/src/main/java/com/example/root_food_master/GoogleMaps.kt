package com.example.root_food_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.root_food_master.databinding.ActivityGoogleMaps2Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMaps : AppCompatActivity(), OnMapReadyCallback {

//    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityGoogleMaps2Binding
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleMaps2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val delhi = LatLng(28.644800,77.216721)
        val mumbai = LatLng(19.0760,72.8777)
        val pune = LatLng(18.5204,73.8567)

        mMap?.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap?.addMarker(MarkerOptions().position(delhi).title("Marker in delhi"))
        mMap?.addMarker(MarkerOptions().position(mumbai).title("Marker in Mumbai"))
        mMap?.addMarker(MarkerOptions().position(pune).title("Marker in pune"))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(delhi))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(mumbai))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(pune))
    }
}