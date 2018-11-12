package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class PhoneNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
        setTitle(R.string.phone_number)
        val nextButton : Button = findViewById(R.id.button)
        nextButton.setOnClickListener {
            val intent = Intent(this, detailsActivity::class.java)
            // start your next activity
            startActivity(intent)

        }
    }


}
