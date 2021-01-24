package com.platzi.conf.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.platzi.conf.databinding.ItemSpeakerBinding
import com.platzi.conf.model.Speaker

class SpeakerAdapter(val speakerListener: SpeakerListener) :
    RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    private var listSpeakers = ArrayList<Speaker>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        LayoutInflater.from(parent.context).inflate(
//            R.layout.item_speaker, parent, false
//        )

        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            //        LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
            ItemSpeakerBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount() = listSpeakers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = listSpeakers[position]

        holder.bind(speaker)

        holder.itemView.setOnClickListener {
            speakerListener.onSpeakerClicked(speaker, position)
        }

    }

    fun updateData(data: List<Speaker>) {
        listSpeakers.clear()
        listSpeakers.addAll(data)
        notifyDataSetChanged()
    }

    //    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class ViewHolder(val binding: ItemSpeakerBinding) : RecyclerView.ViewHolder(binding.root) {
//        val tvSpeakerName = itemView.findViewById<TextView>(R.id.tvItemSpeakerName)
//        val tvSpeakerWork = itemView.findViewById<TextView>(R.id.tvItemSpeakerWork)
//        val ivSpeakerImage = itemView.findViewById<ImageView>(R.id.ivItemSpeakerImage)

        fun bind(speaker: Speaker) {
            binding.tvItemSpeakerName.text = speaker.name
            binding.tvItemSpeakerWork.text = speaker.workplace

//            ImageUtils.loadImageDrawableUrl(itemView.context, speaker.photo, binding.ivItemSpeakerImage)

            Glide.with(itemView)
                .load(speaker.image)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivItemSpeakerImage)

        }
    }
}