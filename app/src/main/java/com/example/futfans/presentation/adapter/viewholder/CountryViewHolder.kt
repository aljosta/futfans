package com.example.futfans.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.transform.RoundedCornersTransformation
import com.example.futfans.databinding.CountryItemBinding
import com.example.futfans.loadSvgFromUrl
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.setOnSafeClickListener
import com.example.futfans.value

class CountryViewHolder(
    private val binding: CountryItemBinding,
    private val actionItem: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: CountryModel) {
        binding.countryNameTextView.text = country.name
        binding.countryImageView.loadSvgFromUrl(country.image) {
            transformations(RoundedCornersTransformation(200f))
            decoderFactory { result, options, _ ->
                SvgDecoder(result.source, options)
            }
        }
        binding.root.setOnSafeClickListener {
            actionItem(country.name.value())
        }
    }
}
