package com.example.mymarvel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymarvel.R
import com.example.mymarvel.databinding.ItemCharacterBinding
import com.example.mymarvel.domain.model.CharacterModel

class HomeAdapter(
    private val context: Context,
    var itemList: ArrayList<CharacterModel>
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    //private var characterList = ArrayList<CharacterModel>()

    fun setCharacterList(characterList: ArrayList<CharacterModel>) {
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(itemList[position], position)
    }

    inner class HomeViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterModel, position: Int) = with(binding) {
            val list = itemList[position]
            binding.txtCharacterName.text = list.name
            val imageUrl =
                "${list.thumbnail.replace("http", "https")}/portrait_xlarge.${list.thumbnailExt}"

            Glide.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.gradient)
                .centerCrop()
                .into(binding.imgCharacter)

            txtCharacterName.text = item.name
        }
    }
}