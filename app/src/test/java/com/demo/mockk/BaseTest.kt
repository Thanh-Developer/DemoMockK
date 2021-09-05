package com.demo.mockk

import io.mockk.MockKAnnotations
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.stubbing.OngoingStubbing

open class BaseTest {

    @Before
    open fun setUp() {
        // make annotation work
        MockitoAnnotations.initMocks(this)
        MockKAnnotations.init(this, relaxUnitFun = true)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    /**
     *  Call this fun for Object class to avoid non-null error in kotlin
     */
    fun <T> any(type: Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    fun <T> uninitialized(): T = null as T

    fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
}