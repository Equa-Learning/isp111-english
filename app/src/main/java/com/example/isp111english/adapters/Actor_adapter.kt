package com.example.isp111english.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.data.model.PictureWithTitleAndDescription

class Actor_adapter(val context:Context, val list: ArrayList<PictureWithTitleAndDescription>): RecyclerView.Adapter<Actor_adapter.Link>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Link {
        val root = LayoutInflater.from(context).inflate(R.layout.actor_item, parent, false)
        return Link(root)
    }

    override fun onBindViewHolder(holder: Link, position: Int) {
        holder.actorImg.setImageResource(list[position].imageId)
        holder.actorTitle.text = list[position].title
        holder.actorDesc.text = list[position].desc
    }

    class Link(view: View): RecyclerView.ViewHolder(view) {
        val actorImg:ImageView = view.findViewById(R.id.image)
        val actorTitle:TextView = view.findViewById(R.id.title)
        val actorDesc:TextView = view.findViewById(R.id.description)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}