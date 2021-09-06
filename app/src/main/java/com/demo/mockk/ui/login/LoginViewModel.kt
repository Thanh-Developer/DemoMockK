package com.demo.mockk.ui.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.mockk.R
import com.demo.mockk.data.remote.LoginRequest
import com.demo.mockk.data.remote.LoginResponse
import com.demo.mockk.data.repository.LoginRepository
import com.demo.mockk.utils.ValidateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Create by ThanhPQ
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
    val obsMessageError = MutableLiveData<Int>()
    val isLoginSuccess = MutableLiveData<Boolean>()

    @VisibleForTesting
    fun checkValidate(): Boolean {
        if (!validateUtils.validateUsername(obsUsername.value.toString()) ||
            !validateUtils.validatePassword(obsPassword.value.toString())
        ) {
            obsMessageError.value = R.string.incorrect_username_password
            return false
        }
        return true
    }

    @VisibleForTesting
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
            }.subscribe({ loginResponse ->
                onLoadSuccess(loginResponse)
            }, {
                onLoadFail()
            })
            )
        }
    }

    fun onLoadSuccess(loginResponse: LoginResponse) {
        print("LoginResponse: $loginResponse")
        isLoginSuccess.value = true
    }

    fun onLoadFail() {
        isLoginSuccess.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
