package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

// This is the welcome activity. This shows the initial welcome screen
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        //Creates the initial Bundle
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Configuring the get started text button */
        val getStartedText : TextView = findViewById(R.id.textView3)

        //On the click of the getStarted button we move on to the next screen which is phone number activity
        getStartedText.setOnClickListener {
            val intent = Intent(this, PhoneNumberActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

    }

}
