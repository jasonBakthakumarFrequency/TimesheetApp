package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class CurrentJob : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_job)
        setTitle(R.string.current_job)

    }
}
