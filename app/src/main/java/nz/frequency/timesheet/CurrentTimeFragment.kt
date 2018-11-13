package nz.frequency.timesheet

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CurrentTimeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var currentView =  inflater.inflate(R.layout.current_time_fragment_layout, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map2) as SupportMapFragment

        return currentView
    }

    override fun onResume() {
        super.onResume()
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Auckland and move the camera
        val auckland = LatLng(-36.8485, 174.7633)

        val circleOptions = CircleOptions()
        circleOptions.center(auckland)
        circleOptions.radius(850.0)
        circleOptions.fillColor(Color.LTGRAY)
        circleOptions.strokeColor(Color.BLACK)
        circleOptions.strokeWidth(3f)
        mMap.addCircle(circleOptions)
        mMap.addMarker(MarkerOptions().position(auckland).title("Marker in Auckland"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(auckland))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.cameraPosition.zoom + 10f))

    }



    companion object {
        fun newInstance(): CurrentTimeFragment = CurrentTimeFragment()
    }
}