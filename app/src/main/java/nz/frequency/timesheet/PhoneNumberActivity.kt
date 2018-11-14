package nz.frequency.timesheet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

//This is the phone number activity that users will enter the phone number in
class PhoneNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)

        //Set the title of the app bar to Phone Number
        setTitle(R.string.phone_number)
        val nextButton : Button = findViewById(R.id.button)

        //When the next Button is clicked we will move into the details activity, showing the details of the user
        nextButton.setOnClickListener {
            val intent = Intent(this, detailsActivity::class.java)
            // start your next activity
            startActivity(intent)

        }
    }


}
