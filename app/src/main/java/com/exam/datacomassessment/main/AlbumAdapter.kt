package com.exam.datacomassessment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exam.datacomassessment.R
import com.exam.datacomassessment.databinding.ItemAlbumBinding
import com.exam.datacomassessment.model.Album

/**
 * Created by Darryl Dave P. de Castro on 11/3/2022.
 */
class AlbumAdapter : ListAdapter<Album, AlbumAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
            oldItem == newItem
    }
) {

    private var onItemClickListener: (Int) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_album,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val item = currentList[holder.absoluteAdapterPosition]
            holder.binding.album = item

            holder.binding.root.setOnClickListener {
                onItemClickListener.invoke(item.id)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setOnItemClickListener(onItemClickListener: (Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)
}