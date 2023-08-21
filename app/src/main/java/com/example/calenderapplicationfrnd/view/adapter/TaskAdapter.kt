package com.example.calenderapplicationfrnd.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calenderapplicationfrnd.R
import com.example.calenderapplicationfrnd.databinding.ItemTaskListBinding
import com.example.calenderapplicationfrnd.utils.debouncedOnClick
import com.example.calenderapplicationfrnd.view.callback.TaskItemClickListener


class TaskAdapter(
    private val onItemListener: TaskItemClickListener
) : androidx.recyclerview.widget.ListAdapter<TaskItemViews,RecyclerView.ViewHolder>(DIFF_UTIl) {

    private fun getListItem(pos: Int) = if (itemCount > 0 && pos in 0 until itemCount) getItem(pos) else null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_task_list, parent, false)
        return TaskItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getListItem(holder.adapterPosition) ?: return
        when {
            holder is TaskItemViewHolder && item is TaskItemViews.TaskItem -> holder.bind(item)
        }
    }

    fun removeItemFromList(pos: Int){
        val list = currentList.toMutableList()
        list.removeAt(pos)
        submitList(list)
    }

    private inner class TaskItemViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val binding: ItemTaskListBinding = ItemTaskListBinding.bind(itemView)

        init {
            view.debouncedOnClick{
                val item = (getListItem(adapterPosition) ?: return@debouncedOnClick) as? TaskItemViews.TaskItem
                    ?: return@debouncedOnClick
                onItemListener.onTaskItemClicked(item)
            }
        }

        fun bind(data: TaskItemViews.TaskItem) {
            with(itemView) {
                with(binding) {
                    parentCardViewTaskItem.setBackgroundResource(R.drawable.bg_card_background);
                    titleText.text = data.title
                    descriptionText.text = data.description
                    if(!data.date.isNullOrEmpty()){
                        dateText.visibility = VISIBLE
                        dateText.text = data.date
                    }else{
                        dateText.visibility = GONE
                    }
                    cellNumberText.text = "T"
                }
            }
        }
    }

    companion object {
        private val DIFF_UTIl = object : DiffUtil.ItemCallback<TaskItemViews>() {

            override fun areItemsTheSame(oldItem: TaskItemViews, newItem: TaskItemViews) : Boolean {
                return when {
                    oldItem is TaskItemViews.TaskItem && newItem is TaskItemViews.TaskItem -> oldItem.taskId == newItem.taskId
                    else -> false
                }
            }

            override fun areContentsTheSame(oldItem: TaskItemViews, newItem: TaskItemViews) = oldItem == newItem
        }
    }
}

sealed class TaskItemViews() {
    data class TaskItem(val title: String?, val description: String?, val date: String? = null, val taskId: Int? = null) : TaskItemViews()
}