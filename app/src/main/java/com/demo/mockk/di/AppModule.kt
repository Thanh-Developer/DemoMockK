package com.demo.mockk.di

import com.demo.mockk.utils.ValidateUtils
import org.koin.dsl.module

/**
 * Created by ThanhPQ
 */
val validateModule = module {
    single { ValidateUtils(get()) }
}
