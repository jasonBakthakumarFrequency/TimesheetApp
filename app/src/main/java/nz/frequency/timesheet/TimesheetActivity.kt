package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.widget.Toast

class TimesheetActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)
        toolbar = supportActionBar!!

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        bottomNavigation.selectedItemId=R.id.action_favorites

    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_favorites -> {
                //current Job

                toolbar.title = "Current Time"
                val currenttimefragmenth = CurrentTimeFragment.newInstance()
                openFragment(currenttimefragmenth)

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_schedules -> {


                toolbar.title = "Time Log"
                val fragment2 = TimeLogFragment.newInstance()
                openFragment(fragment2)


                return@OnNavigationItemSelectedListener true
            }
            R.id. action_music-> {

                toolbar.title = "Health And Safety"
                val fragment3 = HealthAndSafetyFragment.newInstance()
                openFragment(fragment3)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }





}
