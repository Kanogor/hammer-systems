package ru.kanogor.hammersystems.presentation.citylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kanogor.hammersystems.databinding.CityItemsBinding

class CityAdapter : ListAdapter<String, CityViewHolder>(CityDiffUtilCallback()) {

    var onItemClick: ((String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            city.text = item
            root.setOnClickListener {
                item?.let {
                    onItemClick?.invoke(item)
                }
            }
        }
    }
}

class CityDiffUtilCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

}

class CityViewHolder(val binding: CityItemsBinding) : RecyclerView.ViewHolder(binding.root)