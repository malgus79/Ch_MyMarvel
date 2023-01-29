package com.example.mymarvel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymarvel.R
import com.example.mymarvel.databinding.ItemCharacterBinding
import com.example.mymarvel.domain.model.CharacterModel

class HomeAdapter : PagingDataAdapter<CharacterModel, HomeAdapter.HomeViewHolder>(diffCallback) {

    inner class HomeViewHolder(
        val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
            override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel):
                    Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel):
                    Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            holder.itemView.apply {

                val list = getItem(position)
                val imageUrl = "${list?.thumbnail?.replace("http", "https")}" +
                        "/portrait_xlarge.${list?.thumbnailExt}"

                Glide.with(this)
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.gradient)
                    .centerCrop()
                    .into(imgCharacter)

                txtCharacterName.text = list?.name
            }
        }
    }
}