package nz.frequency.timesheet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Shows the time log of a user
 * */

class TimeLogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.time_log_fragment_layout, container, false)
        // Lookup the recyclerview in activity layout
        val rvContacts = fragmentView.findViewById(R.id.rv) as RecyclerView

        val dummyTimeHistory : ArrayList<TimesheetHistory> = populateDummyTimeSheet()

        // Create adapter passing in the sample user data
        val adapter = TimeHistoryAdapter(dummyTimeHistory, activity!!)
        // Attach the adapter to the recyclerview to populate items
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(activity)
        return fragmentView
    }

    //populate the a dummy set of timesheet records
    private fun populateDummyTimeSheet() : ArrayList<TimesheetHistory>{
        var timeList : ArrayList<TimesheetHistory> = ArrayList()

        var i = 0
        while (i < 40){
            var timeData = TimesheetHistory("July 3, 2018", "4:30 PM", "3:20 Min")
            timeList.add(timeData)
            i++
        }

        return timeList
    }

    companion object {
        fun newInstance(): TimeLogFragment = TimeLogFragment()
    }
}