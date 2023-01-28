package com.example.mymarvel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymarvel.R
import com.example.mymarvel.core.BaseViewHolder
import com.example.mymarvel.databinding.ItemCharacterBinding
import com.example.mymarvel.model.data.Result

class HomeAdapter(
    private val context: Context
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var characterList = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(context), parent, false)
        return HomeViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is HomeViewHolder -> holder.bind(characterList[position], position)
        }
    }

    private inner class HomeViewHolder(private val binding: ItemCharacterBinding) :
        BaseViewHolder<Result>(binding.root) {

        override fun bind(item: Result, position: Int) = with(binding) {
            Glide.with(context)
                .load(item)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.gradient)
                .centerCrop()
                .into(imgCharacter)

            txtCharacterName.text = item.name
        }
    }
}