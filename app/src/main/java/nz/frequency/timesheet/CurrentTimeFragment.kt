package nz.frequency.timesheet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CurrentTimeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.current_time_fragment_layout, container, false)

    companion object {
        fun newInstance(): CurrentTimeFragment = CurrentTimeFragment()
    }
}