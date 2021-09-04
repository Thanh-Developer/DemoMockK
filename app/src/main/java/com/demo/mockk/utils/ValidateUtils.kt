package com.demo.mockk.utils

import android.content.Context

/**
 * Created by ThanhPQ
 */
class ValidateUtils(private val context: Context) {

    companion object {
        const val MIN_CHARACTER_PASSWORD = 3
    }

    fun validateUsername(email: String): Boolean = when {
        email.isEmpty() -> false
        else -> true
    }

    fun validatePassword(password: String): Boolean = when {
        password.isEmpty() -> false
        password.trim().isEmpty() -> false
        password.length < MIN_CHARACTER_PASSWORD -> false
        else -> true
    }

}
