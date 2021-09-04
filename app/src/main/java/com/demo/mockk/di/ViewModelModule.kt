package com.demo.mockk.di

import com.demo.mockk.ui.login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  Create by ThanhPQ
 */
val viewModelModule = module {
    viewModel {
        LoginViewModel(get(), get())
    }
}
