package com.demo.mockk

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

val isDisplayed: ViewAssertion = matches(isDisplayed())

fun toolbarWithTitle(@StringRes title: Int): ViewInteraction =
    onView(allOf(withText(title), withParent(isAssignableFrom(Toolbar::class.java))))

fun text(@StringRes resource: Int): ViewInteraction = onView(withText(resource))

infix fun ViewInteraction.check(action: ViewAssertion): ViewInteraction = this.check(action)

infix fun ViewInteraction.hasHint(@StringRes string: Int): ViewInteraction =
    this check matches(withHint(string))

infix fun ViewInteraction.hasText(@StringRes string: Int): ViewInteraction =
    this check matches(withText(string))

infix fun Int.perform(action: ViewAction): ViewInteraction = onView(withId(this)).perform(action)

infix fun Int.check(action: ViewAssertion): ViewInteraction = onView(withId(this)).check(action)

infix fun Int.hasHint(@StringRes resource: Int): ViewInteraction =
    onView(withId(this)) hasHint resource

infix fun Int.hasText(@StringRes resource: Int): ViewInteraction =
    onView(withId(this)) hasText resource