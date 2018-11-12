package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getStartedText : TextView = findViewById(R.id.textView3)
        getStartedText.setOnClickListener {
            val intent = Intent(this, PhoneNumberActivity::class.java)
            // start your next activity
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()


    }
}
