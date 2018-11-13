package nz.frequency.timesheet

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.time.LocalDateTime
import android.os.CountDownTimer
import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


class CurrentTimeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var timeClockedInText : TextView
    private lateinit var startBreakText : TextView
    private lateinit var endBreakText : TextView
    private lateinit var startBreakButton: Button
    private lateinit var endBreakButton: Button
    private lateinit var clockOutButton: Button
    private lateinit var timerText: TextView
    private var pressStartBreak = false
    private var pressEndBreak = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var currentView =  inflater.inflate(R.layout.current_time_fragment_layout, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map2) as SupportMapFragment
        timeClockedInText = currentView.findViewById(R.id.textView23)
        startBreakText = currentView.findViewById(R.id.textView26)
        endBreakText = currentView.findViewById(R.id.textView25)
        startBreakButton = currentView.findViewById(R.id.button4)
        endBreakButton = currentView.findViewById(R.id.button5)
        clockOutButton = currentView.findViewById(R.id.button6)
        timerText = currentView.findViewById(R.id.textView24)
        return currentView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()


        timer(3600000,1000).start()


        mapFragment.getMapAsync(this)
        timeClockedInText.text = "Time Clocked In : " + LocalDateTime.now().toString().substring(11).dropLast(4)

        startBreakButton.setOnClickListener {
            if(!pressStartBreak){
                startBreakText.visibility = View.VISIBLE
                startBreakText.text = "Break started : " + LocalDateTime.now().toString().substring(11).dropLast(4)
                pressStartBreak = true
            }

        }


        endBreakButton.setOnClickListener {
            if(startBreakText.visibility == View.VISIBLE){
                if(!pressEndBreak){
                    endBreakText.visibility = View.VISIBLE
                    endBreakText.text = "Break ended : " + LocalDateTime.now().toString().substring(11).dropLast(4)
                    pressEndBreak = true
                }
            }
            else{
                Toast.makeText(activity, "You have to start a break to end one", Toast.LENGTH_LONG).show()
            }
        }

    }


    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                    var numToShow = abs(millisUntilFinished - 3600000 )
                    timerText.text = timeString(numToShow)

            }

            override fun onFinish() {
                //stuff to do

            }
        }
    }


    // Method to get days hours minutes seconds from milliseconds
    private fun timeString(millisUntilFinished:Long):String{
        var millisUntilFinished:Long = millisUntilFinished
        val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
        millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

        val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
        val formatter = DecimalFormat("00")
        return formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds)

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