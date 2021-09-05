package com.demo.mockk.utils

import androidx.annotation.VisibleForTesting
import java.util.regex.Pattern

/**
 * Created by ThanhPQ
 */
class ValidateUtils {

    companion object {
        const val MIN_CHARACTER_PASSWORD = 3
    }

    @VisibleForTesting
    fun isWhitespace(s: String): Boolean {
        for (element in s) {
            if (Character.isWhitespace(element)) {
                return true
            }
        }
        return false
    }

    @VisibleForTesting
    fun validateUsername(email: String?): Boolean = when {
        email.isNullOrEmpty() -> false
        email.isBlank() -> false
        isWhitespace(email) -> false
        else -> true
    }

    @VisibleForTesting
    fun validatePassword(password: String?): Boolean = when {
        password.isNullOrEmpty() -> false
        password.isBlank() -> false
        isWhitespace(password) -> false
        password.length < MIN_CHARACTER_PASSWORD -> false
        else -> true
    }

}
