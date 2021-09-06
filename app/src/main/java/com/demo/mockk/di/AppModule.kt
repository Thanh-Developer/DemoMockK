package com.demo.mockk.di

import com.demo.mockk.utils.ValidateUtils
import org.koin.dsl.module

/**
 * Create by ThanhPQ
 */
val validateModule = module {
    single { ValidateUtils() }
}
