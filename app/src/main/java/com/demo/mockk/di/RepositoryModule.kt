package com.demo.mockk.di

import com.demo.mockk.data.repository.LoginRepository
import org.koin.dsl.module

/**
 *  Create by ThanhPQ
 */
val repositoryModule = module {
    single {
        LoginRepository(get())
    }
}
