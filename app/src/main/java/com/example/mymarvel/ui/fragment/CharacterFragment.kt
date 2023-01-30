package com.example.mymarvel.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mymarvel.databinding.FragmentCharacterBinding
import com.example.mymarvel.domain.model.CharacterModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private lateinit var character: CharacterModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)

        requireArguments().let {
            CharacterFragmentArgs.fromBundle(it).also { args ->
                character = args.characterModel
            }
        }

        showDataDetails()

        return binding.root
    }

    private fun showDataDetails() {

        val imageUrl = character.thumbnail.replace("http", "https") +
                "/portrait_xlarge.${character.thumbnailExt}"

        Glide.with(requireContext())
            .load(imageUrl)
            .centerCrop()
            .into(binding.imgAppBar)

        with (binding) {
            txtCharacterName.text = character.name
            txtCharacterDescription.text = character.description
            //txtCharacterComic.text= character.comics.toString()
        }
    }
}