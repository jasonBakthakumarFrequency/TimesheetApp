package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

// This activity represents the details of the user
class detailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Setting the title of the app bar to "Details"
        setTitle(R.string.details)
        val verificationButton : Button = findViewById(R.id.button2)

        //After the user verification code is verified we move on to show the current job that user might be working on.
        verificationButton.setOnClickListener {
            //Code to move to the next activity
            val separateIntent = Intent(this, CurrentJobActivity::class.java)
            startActivity(separateIntent)
        }
    }

}
