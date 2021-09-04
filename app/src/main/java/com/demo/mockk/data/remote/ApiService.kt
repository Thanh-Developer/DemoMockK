package com.demo.mockk.data.remote

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by ThanhPQ
 */
interface ApiService {
    @POST("authenticate/")
    fun userLogin(@Body loginRequest: LoginRequest?): Single<LoginResponse>
}
