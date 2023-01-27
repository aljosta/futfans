package com.example.futfans.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futfans.databinding.CountryItemBinding
import com.example.futfans.databinding.TeamItemBinding
import com.example.futfans.presentation.adapter.viewholder.CountryViewHolder
import com.example.futfans.presentation.adapter.viewholder.TeamViewHolder
import com.example.futfans.presentation.model.CountryModel
import com.example.futfans.presentation.model.TeamModel
import com.example.futfans.presentation.model.ViewHolderType

class ListAdapter<T>(
    private var items: List<T>,
    private val actionItem: (String) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItems(newItems: List<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewHolderType.COUNTRY_ITEM_TYPE.value -> CountryViewHolder(
                CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                actionItem
            )
            else -> TeamViewHolder(
                TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                actionItem
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CountryViewHolder -> holder.bind((items[position] as CountryModel))
            is TeamViewHolder -> holder.bind((items[position] as TeamModel))
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is CountryModel -> ViewHolderType.COUNTRY_ITEM_TYPE.value
            else -> ViewHolderType.TEAM_ITEM_TYPE.value
        }
    }
}
