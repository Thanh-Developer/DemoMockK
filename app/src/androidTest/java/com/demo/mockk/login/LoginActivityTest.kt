package com.demo.mockk.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.demo.mockk.login.LoginTestRobot.Companion.loginScreen
import com.demo.mockk.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun shouldContainLoginTitle() {
        loginScreen {
            isDisplayedLoginTitle()
        }
    }

    @Test
    fun loginTitleShouldApplyCorrectText() {
        loginScreen {
            loginTitle {
                hasCorrectTitle()
            }
        }
    }

    @Test
    fun shouldContainUsernameInput() {
        loginScreen {
            isDisplayedUsernameInput()
        }
    }

    @Test
    fun usernameInputShouldApplyCorrectHint() {
        loginScreen {
            usernameEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainPasswordInput() {
        loginScreen {
            isDisplayedPasswordInput()
        }
    }

    @Test
    fun passwordInputShouldApplyCorrectHint() {
        loginScreen {
            passwordEditor {
                hasCorrectHint()
            }
        }
    }

    @Test
    fun shouldContainLoginButton() {
        loginScreen {
            isDisplayedLoginButton()
        }
    }

    @Test
    fun loginButtonShouldApplyCorrectText() {
        loginScreen {
            loginButton {
                hasCorrectTitle()
            }
        }
    }

    @Test
    fun loginWithUsernameHaveBlankShouldDisplayError() {
        loginScreen {
            typeUsername("  ")
            typePassword("Pass@12")
        } submit {
            displayBlankUsernameOrPasswordError()
        }
    }

    @Test
    fun loginWithPasswordHaveBlankShouldDisplayError() {
        loginScreen {
            typeUsername("larn203")
            typePassword("  ")
        } submit {
            displayBlankUsernameOrPasswordError()
        }
    }

    @Test
    fun loginFailShouldDisplayError() {
        loginScreen {
            typeUsername("123456")
            typePassword("123456")
        } submit {
            displayLoginFail()
        }
    }

    @Test
    fun successfulLoginShouldOpenMainScreen() {
        loginScreen {
            typeUsername("larn203")
            typePassword("Pass@12")
        } submit {
            openMainActivity()
        }
    }
}