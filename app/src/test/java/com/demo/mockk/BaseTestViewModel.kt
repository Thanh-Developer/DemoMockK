package com.demo.mockk

import androidx.lifecycle.ViewModel

open class BaseTestViewModel<T : ViewModel> : BaseTest() {
    protected lateinit var viewModel: T
}