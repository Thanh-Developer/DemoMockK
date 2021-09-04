package com.demo.mockk.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.mockk.data.remote.LoginRequest
import com.demo.mockk.data.repository.LoginRepository
import com.demo.mockk.utils.ValidateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ThanhPQ
 *
 * Account demo login
 * @UserName1: larn203
 * @PassWord1: Pass@12
 *
 * @UserName2: larn202
 * @PassWord2: Pass@12
 */
class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val validateUtils: ValidateUtils
) : ViewModel() {
    private val disposable = CompositeDisposable()
    val obsUsername = MutableLiveData<String>()
    val obsPassword = MutableLiveData<String>()
    val obsMessageError = MutableLiveData<String>()
    val isLoginSuccess = MutableLiveData<Boolean>()

    private fun checkValidate(): Boolean {
        if (!validateUtils.validateUsername(obsUsername.value.toString()) ||
            !validateUtils.validatePassword(obsPassword.value.toString())
        ) {
            obsMessageError.value = "username and password cannot be blank or have spaces"
            return false
        }
        return true
    }

    fun onLogin() {
        if (checkValidate()) {
            disposable.add(loginRepository.userLogin(
                LoginRequest(
                    obsUsername.value.toString(),
                    obsPassword.value.toString()
                )
            ).compose { func ->
                func.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }.doOnSubscribe {
                print("Show loading")
            }.doFinally {
                print("Hide loading")
            }.doOnSuccess { loginResponse ->
                print("LoginResponse: $loginResponse")
            }.subscribe({
                isLoginSuccess.value = true
            }, {
                isLoginSuccess.value = false
            })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposable != null) {
            disposable.clear()
        }
    }
}
