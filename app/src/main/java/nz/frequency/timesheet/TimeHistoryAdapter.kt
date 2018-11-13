package nz.frequency.timesheet

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_view_item_layout.view.*


class TimeHistoryAdapter(val items : ArrayList<TimesheetHistory>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_item_layout, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.dateTextView.text = item.timeHistoryDateText
        holder.timeTextView.text = item.timeHistoryTimeText
        holder.durationTextView.text = item.timeHistoryDurationText
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val dateTextView: TextView = view.findViewById(R.id.textView20)
    val timeTextView: TextView = view.findViewById(R.id.textView21)
    val durationTextView: TextView = view.findViewById(R.id.textView22)
}


