package nz.frequency.timesheet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.widget.Toast

/**
 * This is the central activity of the application
 * This records the time worked by the user
 * It also has a geo fencing feature that enforces the user to remain within 100m radius of the site
 * */


class TimesheetActivity : AppCompatActivity() {

    //References the toolbar
    lateinit var toolbar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet)

        //Assign the action Bar to the exiting supportActionBar
        toolbar = supportActionBar!!

        //Getting a reference to the bottom navigation view
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        //listening to the navigation item changes
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        //selecting the first option by default
        bottomNavigation.selectedItemId=R.id.action_favorites

    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_favorites -> {
                //Changes Title of the toolbar to current job.
                toolbar.title = "Current Time"
                //Keeps the current time fragment as the selected option after which it replaces the view in the frame container
                val currenttimefragmenth = CurrentTimeFragment.newInstance()
                openFragment(currenttimefragmenth)

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_schedules -> {

                //Changes Title of the toolbar to Time Log.
                toolbar.title = "Time Log"

                //Keeps the time log fragment as the selected option after which it replaces the view in the frame container
                val fragment2 = TimeLogFragment.newInstance()
                openFragment(fragment2)


                return@OnNavigationItemSelectedListener true
            }
            R.id. action_music-> {

                //Changes Title of the toolbar to Health and Safety
                toolbar.title = "Health And Safety"

                //Keeps the Health and Safety fragment as the selected option after which it replaces the view in the frame container
                val fragment3 = HealthAndSafetyFragment.newInstance()
                openFragment(fragment3)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //Method carries out the fragment transaction
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }





}
