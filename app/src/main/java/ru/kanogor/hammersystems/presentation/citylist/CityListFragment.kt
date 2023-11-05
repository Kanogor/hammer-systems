package ru.kanogor.hammersystems.presentation.citylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kanogor.hammersystems.R
import ru.kanogor.hammersystems.databinding.FragmentCityListBinding

class CityListFragment : Fragment() {

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Выберите Ваш город"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val cityList = listOf(
            "Москва",
            "Санкт-Петербург",
            "Казань",
            "Волгоград",
            "Самара",
            "Саратов",
            "Нижний Новгород",
            "Астрахань",
            "Ростов-на-Дону",
            "Краснодар",
            "Сочи"
        )

        val cityAdapter = CityAdapter()
        binding.recyclerViewCity.adapter = cityAdapter
        cityAdapter.submitList(cityList)
        cityAdapter.onItemClick = { city ->
            (activity as AppCompatActivity).supportActionBar?.title = city
            Toast.makeText(requireContext(), "Вы выбрали город $city", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
            findNavController().navigate(R.id.menu)
        }
    }
}