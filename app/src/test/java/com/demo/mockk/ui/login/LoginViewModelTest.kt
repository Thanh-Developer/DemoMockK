package com.demo.mockk.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.mockk.BaseTestViewModel
import com.demo.mockk.data.remote.LoginResponse
import com.demo.mockk.data.repository.LoginRepository
import com.demo.mockk.utils.ValidateUtils
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Single
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
 * Create by ThanhPQ
 */
class LoginViewModelTest : BaseTestViewModel<LoginViewModel>() {
    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var loginRepository: LoginRepository

    @MockK
    private lateinit var validateUtils: ValidateUtils

    private val loginTest = LoginResponse(1, "larn203", "Pass@12")

    override fun setUp() {
        super.setUp()
        viewModel = spyk(LoginViewModel(loginRepository, validateUtils))
    }

    @Test
    fun checkValidateUserNameFail() {
        //Given
        every { validateUtils.validateUsername(any()) } returns false
        every { validateUtils.validatePassword(any()) } returns true

        //Then
        assertFalse(viewModel.checkValidate())
    }

    @Test
    fun checkValidatePasswordFail() {
        //Given
        every { validateUtils.validateUsername(any()) } returns true
        every { validateUtils.validatePassword(any()) } returns false

        //Then
        assertFalse(viewModel.checkValidate())
    }

    @Test
    fun checkValidateSuccess() {
        //Given
        every { validateUtils.validateUsername(any()) } returns true
        every { validateUtils.validatePassword(any()) } returns true

        //Then
        assertTrue(viewModel.checkValidate())
    }

    @Test
    fun loginFail() {
        //Given
        every { viewModel.checkValidate() } returns true

        every { loginRepository.userLogin(any()) } answers {
            Single.error(Throwable("error"))
        }

        //When
        viewModel.onLogin()

        //Then
        verify(exactly = 1) {
            viewModel.checkValidate()
            viewModel.onLoadFail()
        }
    }

    @Test
    fun loginSuccess() {
        //Given
        every { viewModel.checkValidate() } returns true

        every { loginRepository.userLogin(any()) } answers {
            Single.just(loginTest)
        }

        //When
        viewModel.onLogin()

        //Then
        verify(exactly = 1) {
            viewModel.checkValidate()
            viewModel.onLoadSuccess(any())
        }
    }
}
