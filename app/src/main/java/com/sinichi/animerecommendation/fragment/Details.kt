package com.sinichi.animerecommendation.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sinichi.animerecommendation.R

/**
 * A simple [Fragment] subclass.
 */
class Details : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        val tvJudul = view.findViewById<TextView>(R.id.tv_judul)
        val tvRating = view.findViewById<TextView>(R.id.tv_rating)
        val tvDeskripsi = view.findViewById<TextView>(R.id.tv_deskripsi)
        val imgThumbnail = view.findViewById<ImageView>(R.id.img_thumbnail)

        val arguments = arguments
        val judul = arguments!!.getString("judul")
        val rating = arguments.getString("rating")
        val deskripsi = arguments.getString("deskripsi")
        val thumbnail = arguments.getString("thumbnail")

        tvJudul.text = judul
        tvRating.text = rating
        tvDeskripsi.text = deskripsi
        Glide.with(context!!)
            .load(thumbnail)
            .into(imgThumbnail)


        return view
    }



}
