package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ClockInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock_in)
        setTitle(R.string.time_sheet)
    }
}
