package com.demo.mockk.login

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import com.demo.mockk.*
import java.lang.Thread.sleep

@DslMarker
private annotation class TestRobotMarker

@TestRobotMarker
private interface LoginRobot

class LoginTestRobot : LoginRobot {

    companion object {

        fun loginScreen(block: LoginTestRobot.() -> Unit): LoginTestRobot {
            return LoginTestRobot().apply(block)
        }
    }

    fun isDisplayedLoginTitle() = R.id.tvTitle check isDisplayed

    fun isDisplayedUsernameInput() = R.id.edtUsername check isDisplayed

    fun isDisplayedPasswordInput() = R.id.edtPassword check isDisplayed

    fun isDisplayedLoginButton() = R.id.btnLogin check isDisplayed

    fun typeUsername(username: String) = R.id.edtUsername perform typeText(username)

    fun typePassword(password: String) = R.id.edtPassword perform typeText(password)

    fun usernameEditor(block: UsernameRobot.() -> Unit): UsernameRobot {
        return UsernameRobot().apply(block)
    }

    fun passwordEditor(block: PasswordRobot.() -> Unit): PasswordRobot {
        return PasswordRobot().apply(block)
    }

    fun loginButton(block: LoginButtonRobot.() -> Unit): LoginButtonRobot {
        return LoginButtonRobot().apply(block)
    }

    fun loginTitle(block: LoginTitleRobot.() -> Unit): LoginTitleRobot {
        return LoginTitleRobot().apply(block)
    }

    infix fun submit(block: LoginResult.() -> Unit): LoginResult {
        R.id.btnLogin perform ViewActions.closeSoftKeyboard()
        R.id.btnLogin perform ViewActions.click()
        sleep(2000);
        return LoginResult().apply(block)
    }
}

class UsernameRobot : LoginRobot {
    fun hasCorrectHint() = R.id.edtUsername hasHint R.string.username
}

class PasswordRobot : LoginRobot {
    fun hasCorrectHint() = R.id.edtPassword hasHint R.string.password
}

class LoginButtonRobot : LoginRobot {
    fun hasCorrectTitle() = R.id.btnLogin hasText R.string.login
}

class LoginTitleRobot : LoginRobot {
    fun hasCorrectTitle() = R.id.tvTitle hasText R.string.login
}

class LoginResult : LoginRobot {
    fun displayLoginFail() = text(R.string.login_fail) check isDisplayed

    fun displayBlankUsernameOrPasswordError() =
        text(R.string.incorrect_username_password) check isDisplayed

    fun openMainActivity() = R.id.tvWelcome check isDisplayed
}