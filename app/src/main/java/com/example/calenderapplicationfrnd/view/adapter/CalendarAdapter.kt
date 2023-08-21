package com.example.calenderapplicationfrnd.view.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calenderapplicationfrnd.view.callback.CalendarItemClickListener
import com.example.calenderapplicationfrnd.R
import com.example.calenderapplicationfrnd.databinding.CalendarCellBinding
import com.example.calenderapplicationfrnd.utils.debouncedOnClick
import java.time.LocalDate


class CalendarAdapter(
    private val onItemListener: CalendarItemClickListener
) : androidx.recyclerview.widget.ListAdapter<CalendarItemViews,RecyclerView.ViewHolder>(DIFF_UTIl) {

    private fun getListItem(pos: Int) = if (itemCount > 0 && pos in 0 until itemCount) getItem(pos) else null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.166667).toInt()
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getListItem(holder.adapterPosition) ?: return
        when {
            holder is CalendarViewHolder && item is CalendarItemViews.CalendarDate -> holder.bind(item)
        }
    }

    private inner class CalendarViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding: CalendarCellBinding = CalendarCellBinding.bind(itemView)

        init {
            view.debouncedOnClick{
                val item = (getListItem(adapterPosition) ?: return@debouncedOnClick) as? CalendarItemViews.CalendarDate
                    ?: return@debouncedOnClick
                onItemListener.onCalendarItemClicked(item)
            }
        }

        fun bind(data: CalendarItemViews.CalendarDate) {
            with(itemView) {
                binding.cellDayText.text = data.title
                try {
                    binding.cellDayText.setTextColor(Color.parseColor(data.color))
                }catch (e: Exception){
                    Log.d("Text color setting exception at $this", data.color )
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIl = object : DiffUtil.ItemCallback<CalendarItemViews>() {

            override fun areItemsTheSame(oldItem: CalendarItemViews, newItem: CalendarItemViews) : Boolean {
                return when {
                    oldItem is CalendarItemViews.CalendarDate && newItem is CalendarItemViews.CalendarDate -> oldItem.title == newItem.title
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: CalendarItemViews, newItem: CalendarItemViews) = oldItem == newItem
        }
    }
}

sealed class CalendarItemViews() {
    data class CalendarDate(val title: String, val color: String, val localDate: LocalDate? = null) : CalendarItemViews()
}