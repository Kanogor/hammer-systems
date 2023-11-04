package ru.kanogor.hammersystems.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kanogor.hammersystems.presentation.menu.MenuViewModel

val appModule = module {

    viewModel {
        MenuViewModel(
            repository = get()
        )
    }
}