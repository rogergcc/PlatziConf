package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platzi.conf.databinding.ItemScheduleBinding
import com.platzi.conf.model.Conference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter(val scheduleListener: ScheduleListener) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    var listConference = ArrayList<Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
    //        LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount() = listConference.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        val conference = listConference[position] as Conference

        holder.binding.tvItemScheduleConferenceName.text = conference.title
        holder.binding.tvItemScheduleConferenceSpeaker.text = conference.speaker
        holder.binding.tvItemScheduleTag.text = conference.tag

        val simpleDateformat = SimpleDateFormat("HH:mm")
        val simpleDateformatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime
        val hourFormat = simpleDateformat.format(conference.datetime)

        holder.binding.tvItemScheduleHour.text = hourFormat
        holder.binding.tvItemScheduleAMPM.text =
            simpleDateformatAMPM.format(conference.datetime).toUpperCase()

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClicked(conference, position)
        }

    }

    fun updateData(data: List<Conference>) {
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemScheduleBinding) : RecyclerView.ViewHolder(binding.root) {
//        val tvConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
//        val tvConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
//        val tvConferenceTag = itemView.findViewById<TextView>(R.id.tvItemScheduleTag)
//        val tvConferenceHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)
//        val tvConferenceAMPM = itemView.findViewById<TextView>(R.id.tvItemScheduleAMPM)
    }

}