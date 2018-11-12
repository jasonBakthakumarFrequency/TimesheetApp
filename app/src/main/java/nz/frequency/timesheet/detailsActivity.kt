package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class detailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setTitle(R.string.details)
        val verificationButton : Button = findViewById(R.id.button2)

        verificationButton.setOnClickListener {
            val seperateIntent = Intent(this, CurrentJob::class.java)
            // start your next activity
            startActivity(seperateIntent)
        }
    }

}
