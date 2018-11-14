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
import java.util.concurrent.TimeUnit
import kotlin.math.abs

/**
 * The current time fragment holds the time of the worker
 * It also has the break functionality along with the clock out button
 * */
class CurrentTimeFragment : Fragment(), OnMapReadyCallback {

    //Initiliasing varibles for later use, will be described in the code
    private lateinit var mMap: GoogleMap
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var timeClockedInText : TextView
    private lateinit var startBreakText : TextView
    private lateinit var endBreakText : TextView
    private lateinit var startBreakButton: Button
    private lateinit var endBreakButton: Button
    private lateinit var clockOutButton: Button
    private lateinit var timerText: TextView


    //These variables are kept as flag variables to check whether the user has pressed startBreak or End Break. Based on this variable the seperate timer thread will either start or stop
    private var pressStartBreak = false
    private var pressEndBreak = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Binding the view of the fragment
        var currentView =  inflater.inflate(R.layout.current_time_fragment_layout, container, false)

        //Referencing the map view
        mapFragment = childFragmentManager.findFragmentById(R.id.map2) as SupportMapFragment

        //Text for time clocked In.
        timeClockedInText = currentView.findViewById(R.id.textView23)
        //Text for starting a break.
        startBreakText = currentView.findViewById(R.id.textView26)
        //Text for ending a break.
        endBreakText = currentView.findViewById(R.id.textView25)

        //Button to start a break
        startBreakButton = currentView.findViewById(R.id.button4)

        //Button to end a break
        endBreakButton = currentView.findViewById(R.id.button5)

        //Clock out button
        clockOutButton = currentView.findViewById(R.id.button6)

        //The large textView for the timer
        timerText = currentView.findViewById(R.id.textView24)

        //Returning the view to its view holder
        return currentView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()


        //Sample timer is set for an hour
        timer(3600000,1000).start()

        //The map fragment has a inbuilt call
        mapFragment.getMapAsync(this)

        //Time clocked in is shown
        timeClockedInText.text = "Time Clocked In : " + LocalDateTime.now().toString().substring(11).dropLast(4)

        //when the start break button is pressed
        startBreakButton.setOnClickListener {

            //if start break is previously pressed, we do not do anything.
            if(!pressStartBreak){

                //We make the view visible
                startBreakText.visibility = View.VISIBLE

                //Present the time in which break was started
                startBreakText.text = "Break started : " + LocalDateTime.now().toString().substring(11).dropLast(4)

                //Make the flag true after first call
                pressStartBreak = true
            }

        }


        endBreakButton.setOnClickListener {

            //Check if the start break button is already pressed
            if(startBreakText.visibility == View.VISIBLE){
                if(!pressEndBreak){
                    //if start break is previously pressed, we do not do anything.
                    endBreakText.visibility = View.VISIBLE

                    // Present the time break was ended
                    endBreakText.text = "Break ended : " + LocalDateTime.now().toString().substring(11).dropLast(4)

                    //make the flag true after first press
                    pressEndBreak = true
                }
            }
            else{
                Toast.makeText(activity, "You have to start a break to end one", Toast.LENGTH_LONG).show()
            }
        }

    }


    //Function that returns the configured timer object
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

        //Creating circle options for the map
        val circleOptions = CircleOptions()
        circleOptions.center(auckland)

        //Customising those circle option
        circleOptions.radius(850.0)
        circleOptions.fillColor(Color.LTGRAY)
        circleOptions.strokeColor(Color.BLACK)
        circleOptions.strokeWidth(3f)

        // Adding the customised circle on to the map
        mMap.addCircle(circleOptions)
        // Add a marker to the location
        mMap.addMarker(MarkerOptions().position(auckland).title("Marker in Auckland"))
        // Move the marker to recommended location
        mMap.moveCamera(CameraUpdateFactory.newLatLng(auckland))
        //Zoom the maps camera in to show the view
        mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.cameraPosition.zoom + 10f))

    }



    companion object {
        fun newInstance(): CurrentTimeFragment = CurrentTimeFragment()
    }







}