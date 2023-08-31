package com.example.citySearchApplication.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.citySearchApplication.R
import com.example.citySearchApplication.databinding.ItemCityListBinding


class TaskAdapter : androidx.recyclerview.widget.ListAdapter<CityItemViews,RecyclerView.ViewHolder>(DIFF_UTIl) {

    private fun getListItem(pos: Int) = if (itemCount > 0 && pos in 0 until itemCount) getItem(pos) else null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_city_list, parent, false)
        return TaskItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getListItem(holder.adapterPosition) ?: return
        when {
            holder is TaskItemViewHolder && item is CityItemViews.CityItem -> holder.bind(item)
        }
    }

    fun removeItemFromList(pos: Int){
        val list = currentList.toMutableList()
        list.removeAt(pos)
        submitList(list)
    }

    private inner class TaskItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding: ItemCityListBinding = ItemCityListBinding.bind(itemView)

        fun bind(data: CityItemViews.CityItem) {
            with(itemView) {
                with(binding) {
                    titleCity.text = data.cityName
                    titleState.text = data.stateName
                    titleCountry.text = data.countryName
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIl = object : DiffUtil.ItemCallback<CityItemViews>() {

            override fun areItemsTheSame(oldItem: CityItemViews, newItem: CityItemViews) : Boolean = false

            override fun areContentsTheSame(oldItem: CityItemViews, newItem: CityItemViews) = oldItem == newItem
        }
    }
}

sealed class CityItemViews() {
    data class CityItem(val cityName: String?, val stateName: String?, val countryName: String? = null) : CityItemViews()
}