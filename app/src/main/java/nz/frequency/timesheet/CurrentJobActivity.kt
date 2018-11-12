package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Toast

class CurrentJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_job)
        setTitle(R.string.current_job)

        val fab : FloatingActionButton = findViewById(R.id.floatingActionButton)

        fab.setOnClickListener {
            val intent = Intent(this, ClockInMapsActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
}
