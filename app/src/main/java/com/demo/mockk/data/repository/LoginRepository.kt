package com.demo.mockk.data.repository

import com.demo.mockk.data.remote.ApiService
import com.demo.mockk.data.remote.LoginRequest
import com.demo.mockk.data.remote.LoginResponse
import io.reactivex.Single

/**
 * Create by ThanhPQ
 */
class LoginRepository(private val apiService: ApiService) {
    fun userLogin(loginRequest: LoginRequest): Single<LoginResponse> =
        apiService.userLogin(loginRequest)
}
