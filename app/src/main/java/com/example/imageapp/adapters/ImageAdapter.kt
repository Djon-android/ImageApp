package com.example.imageapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.R
import com.squareup.picasso.Picasso


class ImageAdapter(private val width: Int, private val columns: Int) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    var onClickImageListener: OnClickImageListener? = null

    var listImage: MutableList<String> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnClickImageListener {
        fun onClickImage(urlImage: String)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClickImageListener?.onClickImage(listImage[position])
        }
        Picasso.get().load(listImage[position]).resize(width / columns, width / columns)
            .centerCrop().error(R.drawable.ic_baseline_thumb_up_24).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return listImage.size
    }
}