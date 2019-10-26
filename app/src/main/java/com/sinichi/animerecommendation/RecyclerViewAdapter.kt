package com.sinichi.animerecommendation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_item.view.*

class RecyclerViewAdapter(private val dataList: List<Model>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item,
            parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.view.img_thumbnail.context)
            .load(dataList[position].thumbnail)
            .fallback(R.drawable.high_school_dxd)
            .into(holder.view.img_thumbnail)
        holder.view.tv_judul.text = dataList[position].judul
        holder.view.tv_rating.text = dataList[position].rating
        holder.view.tv_deskripsi.text = dataList[position].deskripsi
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
