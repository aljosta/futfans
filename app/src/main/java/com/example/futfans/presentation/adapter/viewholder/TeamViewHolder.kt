package com.example.futfans.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.futfans.databinding.TeamItemBinding
import com.example.futfans.loadSvgFromUrl
import com.example.futfans.presentation.model.TeamModel
import com.example.futfans.setOnSafeClickListener
import com.example.futfans.value

class TeamViewHolder(
    private val binding: TeamItemBinding,
    private val actionItem: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(team: TeamModel) {
        binding.teamNameTextView.text = team.name
        binding.teamFoundedTextView.text = "Fundado en ${team.founded}"
        binding.teamStadiumTextView.text = team.stadiumName
        binding.teamImageView.loadSvgFromUrl(team.image)
        binding.root.setOnSafeClickListener {
            actionItem(team.name.value())
        }
    }
}
