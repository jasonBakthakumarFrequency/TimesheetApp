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
        // This will be the details of the user
        setTitle(R.string.details)
        val verificationButton : Button = findViewById(R.id.button2)

        //When the code is verified and everything we move on to the next thing
        verificationButton.setOnClickListener {
            val seperateIntent = Intent(this, CurrentJobActivity::class.java)
            // start your next activity
            startActivity(seperateIntent)
        }
    }

}
