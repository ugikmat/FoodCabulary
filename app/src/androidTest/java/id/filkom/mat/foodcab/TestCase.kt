package id.filkom.mat.foodcab

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by mat on 5/16/18.
 */
@RunWith(AndroidJUnit4::class)
class TestCase {


    @get: Rule
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun invalidEmail() {
        //to check view on screen
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("Halo Semuanya"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("Halo emuanya"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_in_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("This email address is invalid")))
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    @Test
    fun emptyEmail() {
        //to check view on screen
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("Halo emuanya"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_in_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("This field is required")))
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }


    @Test
    fun emptyPassword() {
        //to check view on screen
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("hola@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_in_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("This password is too short")))
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    @Test
    fun shortPassword() {
        //to check view on screen
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("hola@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("hola"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_in_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("This password is too short")))
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    @Test
    fun validEmailPassword() {
        //to check view on screen
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("user@mail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("usermail"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_in_button)).perform(ViewActions.click())
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}