package com.demo.mockk.utils

import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Create by ThanhPQ
 */
class ValidateUtilsTest {
    @MockK
    private lateinit var validateUtils: ValidateUtils

    @Before
    fun setUp() {
        validateUtils = ValidateUtils()
    }

    @Test
    fun validateIsWhiteSpaceReturnTrue() {
        assertTrue(validateUtils.isWhitespace(" "))
    }

    @Test
    fun validateIsWhiteSpaceReturnFalse() {
        assertFalse(validateUtils.isWhitespace("123456"))
    }

    @Test
    fun validateUsernameNullReturnFalse() {
        assertFalse(validateUtils.validateUsername(null))
    }

    @Test
    fun validatorUserNameEmptyReturnFalse() {
        assertFalse(validateUtils.validateUsername(""))
    }

    @Test
    fun validatorUserNameBlankReturnFalse() {
        assertFalse(validateUtils.validateUsername("   "))
    }

    @Test
    fun validatorUserNameBlankAtStartReturnFalse() {
        assertFalse(validateUtils.validateUsername(" 123456"))
    }

    @Test
    fun validatorUserNameBlankAtBetweenReturnFalse() {
        assertFalse(validateUtils.validateUsername("123 456"))
    }

    @Test
    fun validatorUserNameBlankAtEndReturnFalse() {
        assertFalse(validateUtils.validateUsername("123456 "))
    }

    @Test
    fun validateUsernameCorrect() {
        assertTrue(validateUtils.validateUsername("123456"))
    }

    @Test
    fun validatePasswordNullReturnFalse() {
        assertFalse(validateUtils.validatePassword(null))
    }

    @Test
    fun validatorPasswordEmptyReturnFalse() {
        assertFalse(validateUtils.validatePassword(""))
    }

    @Test
    fun validatorPasswordBlankReturnFalse() {
        assertFalse(validateUtils.validatePassword("   "))
    }

    @Test
    fun validatorPasswordBlankAtStartReturnFalse() {
        assertFalse(validateUtils.validatePassword(" 123456"))
    }

    @Test
    fun validatorPasswordBlankAtBetweenReturnFalse() {
        assertFalse(validateUtils.validatePassword("123 456"))
    }

    @Test
    fun validatorPasswordBlankAtEndReturnFalse() {
        assertFalse(validateUtils.validatePassword("123456 "))
    }

    @Test
    fun validatorPasswordLessThreeCharacterReturnFalse() {
        assertFalse(validateUtils.validatePassword("12"))
    }

    @Test
    fun validatePasswordCorrect() {
        assertTrue(validateUtils.validatePassword("123456"))
    }
}