package nz.frequency.timesheet

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * The seperate designated fragment for health and safety.
 * It needs to be developed.
 * */
class HealthAndSafetyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.health_and_safety_fragment_layout, container, false)

    companion object {
        fun newInstance(): HealthAndSafetyFragment = HealthAndSafetyFragment()
    }
}