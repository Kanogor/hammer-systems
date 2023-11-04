package ru.kanogor.hammersystems.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.kanogor.hammersystems.R
import ru.kanogor.hammersystems.databinding.PizzaItemBinding
import ru.kanogor.hammersystems.entity.Pizza

class PizzaAdapter : ListAdapter<Pizza, PizzaViewHolder>(PizzaDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val binding = PizzaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val item = getItem(position)
        val nameText = item.name
        val descriptionText =
            if (item.descripcion == null) "Описание отсутствует" else item.descripcion
        val costText = if (item.price == null) "Закончился" else {
            val price = item.price!!.replace(".00", "")
            "от $price руб."
        }
        with(holder.binding) {
            pizzaName.text = nameText
            pizzaDescription.text = descriptionText
            pizzaCost.text = costText
            item.let {
                Glide.with(pizzaImage.context)
                    .load(item.image)
                    .placeholder(R.drawable.ic_menu)
                    .into(pizzaImage)
            }
        }
    }
}

class PizzaDiffUtilCallback() : DiffUtil.ItemCallback<Pizza>() {
    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean =
        oldItem.name == newItem.name

}

class PizzaViewHolder(val binding: PizzaItemBinding) : RecyclerView.ViewHolder(binding.root)