package com.example.isp111english.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.isp111english.R
import com.example.isp111english.data.model.PictureWithTitle

class Frame_adapter(val context:Context,val list: ArrayList<PictureWithTitle>): RecyclerView.Adapter<Frame_adapter.Link>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Link {
        val root = LayoutInflater.from(context).inflate(R.layout.frame_item, parent, false)
        return Link(root)
    }

    override fun onBindViewHolder(holder: Link, position: Int) {
        holder.frame_img.setImageResource(list[position].imageId)
    }

    class Link(view: View): RecyclerView.ViewHolder(view) {
        val frame_img:ImageView = view.findViewById(R.id.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}