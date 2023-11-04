package ru.kanogor.hammersystems.presentation.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kanogor.hammersystems.data.Repository
import ru.kanogor.hammersystems.entity.Pizza

class MenuViewModel (private val repository: Repository) : ViewModel() {

    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas = _pizzas.asStateFlow()
    fun getData() {
            viewModelScope.launch {
                kotlin.runCatching {
                repository.getPizzas()
            }.fold(
                onSuccess = {
                    _pizzas.value = it
                },
                onFailure = {
                    Log.d("MenuVM", it.message ?: "Getting Pizza Info unsuccessful")
                }
            )
        }
    }

}