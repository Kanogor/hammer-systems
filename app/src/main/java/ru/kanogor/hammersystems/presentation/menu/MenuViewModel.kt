package ru.kanogor.hammersystems.presentation.menu

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kanogor.hammersystems.data.ProductosDto
import ru.kanogor.hammersystems.data.Repository
import ru.kanogor.hammersystems.entity.Pizza

const val DB_INFO = "db_info"

class MenuViewModel(
    private val repository: Repository,
    private val pref: SharedPreferences
) : ViewModel() {

    private val _pizzas = MutableStateFlow<List<Pizza>>(emptyList())
    val pizzas = _pizzas.asStateFlow()

    // Функция для сохранения данных в SharedPreferences
    private fun saveData(value: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(DB_INFO, value)
        editor.apply()
    }

    // Функция для извлечения данных из SharedPreferences
    private fun getDataValueString(): String? {
        return pref.getString(DB_INFO, null)
    }

    // Запрос данных с сервера
    fun getData() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getPizzas()
            }.fold(
                // Если запрос удачный - данные сохряняются в SharedPreferences и передаются в RecyclerView
                onSuccess = {
                    _pizzas.value = it.productos
                    val bigJson = Gson().toJson(it)
                    saveData(bigJson)
                },
                //Если нет, то данные беруться из SharedPreferences(если они там есть)
                onFailure = {
                    Log.d("MenuVM", it.message ?: "Getting Pizza Info unsuccessful")
                    if (getDataValueString() != null) {
                        val dataClass = Gson().fromJson(
                            getDataValueString(),
                            ProductosDto::class.java
                        )
                        _pizzas.value = dataClass.productos
                    }
                }
            )
        }
    }
}