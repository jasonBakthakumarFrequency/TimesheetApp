package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Toast

//This is the activity that user choose the job their working on
class CurrentJobActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_job)

        //Setting the title of the activity to current job
        setTitle(R.string.current_job)

        //Binding the floating action button in the view to fab
        val fab : FloatingActionButton = findViewById(R.id.floatingActionButton)

        //Called when Fab is clicked
        fab.setOnClickListener {

            //We move on to the next activity, which shows the Clock and Location of the user.
            val intent = Intent(this, ClockInMapsActivity::class.java)
            startActivity(intent)
        }

    }
}
