package com.sinichi.animerecommendation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinichi.animerecommendation.R
import com.sinichi.animerecommendation.model.Model
import kotlinx.android.synthetic.main.layout_item.view.*

class RecyclerViewAdapter(private val dataList: List<Model>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var adapter: AdapterClick

    interface AdapterClick {
        fun onClick(model: Model, position: Int?)
    }

    fun setOnClickListener(adapterClick: AdapterClick) {
        this.adapter = adapterClick
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item,
                parent, false
            )
        )
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
        holder.view.card_view.setOnClickListener {
            val model = dataList[position]
            adapter.onClick(model, position)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
