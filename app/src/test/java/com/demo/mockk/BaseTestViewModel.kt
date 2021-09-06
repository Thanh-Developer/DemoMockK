package com.demo.mockk

import androidx.lifecycle.ViewModel

/**
 * Create by ThanhPQ
 */
open class BaseTestViewModel<T : ViewModel> : BaseTest() {
    protected lateinit var viewModel: T
}