package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.R.attr.fillColor
import android.R.attr.radius
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.google.android.gms.maps.model.CircleOptions
import java.util.jar.Manifest
import android.support.design.widget.Snackbar
import android.support.v4.view.accessibility.AccessibilityEventCompat.setAction
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_clock_in_maps.*

/**
 * This is the Clock In Maps Activity. It shows the map viwe indicating the location their in and shows the time presenting them with the option to clock in
 *
 * */
class ClockInMapsActivity : AppCompatActivity(), OnMapReadyCallback {
    //Required to configure the Google Maps View
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    //This variable is initialised to make sure that the requested permission is accurate
    private val MY_PERMISSIONS_REQUEST_GET_LOCATION  = 20

    //This constraint layout is referenced because it is required for the Snackbar to be presented
    private lateinit var parentView : ConstraintLayout

    //This is created to reference the clock in button
    private lateinit var clockInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock_in_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        //Setting up the Constraint Layout
        parentView = findViewById(R.id.parent_layout)

        //Setting up the clock in button
        clockInButton = findViewById(R.id.button3)

        //Setting the title of the App to Time Sheet
        setTitle(R.string.time_sheet)

    }




    override fun onResume() {
        super.onResume()

        //The Asynchronous map call is implemented within this class
        mapFragment.getMapAsync(this)


        //Called when clock in button is clicked
        clockInButton.setOnClickListener {


            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                ) {
                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_GET_LOCATION
                    )

                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_GET_LOCATION
                    )

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            } else {
                // Permission has already been granted
                //Just move on to the next activity.
                val intent = Intent(this, TimesheetActivity::class.java)
                // start your next activity
                startActivity(intent)


            }

        }


    }


    //Called after the user requests his/her permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_GET_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Move to the next activity

                    val intent = Intent(this, TimesheetActivity::class.java)
                    // start your next activity
                    startActivity(intent)


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    val snackbar = Snackbar.make(parentView, "We need your location to validate our records. Please try again", Snackbar.LENGTH_LONG)
                    snackbar.show()

                }
                return
            }
        }// other 'case' lines to check for other
        // permissions this app might request.
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

        // Add a marker in Auckland and move the camera
        val auckland = LatLng(-36.8485, 174.7633)

        //Configuring the circle option to display on the map
        val circleOptions = CircleOptions()
        circleOptions.center(auckland)
        circleOptions.radius(850.0)
        circleOptions.fillColor(Color.LTGRAY)
        circleOptions.strokeColor(Color.BLACK)
        circleOptions.strokeWidth(3f)

        //Add the configured circle on to the map
        mMap.addCircle(circleOptions)
        // Represent the marker with a description
        mMap.addMarker(MarkerOptions().position(auckland).title("Marker in Auckland"))
        //Move into the location of the marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(auckland))
        //Zoom into the location of the marker about a 100 meters
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.cameraPosition.zoom + 10f))


    }




}
