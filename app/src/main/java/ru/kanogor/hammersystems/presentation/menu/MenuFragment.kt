package ru.kanogor.hammersystems.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.kanogor.hammersystems.databinding.FragmentMenuBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kanogor.hammersystems.R

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MenuViewModel by viewModel<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel.getData()

        val pizzaAdapter = PizzaAdapter()
        binding.recyclerView.adapter = pizzaAdapter

        viewModel.pizzas.onEach {
            pizzaAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.chipGroup.setOnCheckedStateChangeListener { group, _ ->

            when (group.checkedChipId) {
                R.id.pizza_chip -> {
                    viewModel.pizzas.onEach {
                        pizzaAdapter.submitList(it)
                    }.launchIn(viewLifecycleOwner.lifecycleScope)
                }

                else -> {
                    pizzaAdapter.submitList(emptyList())
                    Toast.makeText(requireContext(), "Товары отсутствуют", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

    }

}