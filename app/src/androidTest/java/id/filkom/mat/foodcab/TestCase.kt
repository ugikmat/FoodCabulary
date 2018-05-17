package id.filkom.mat.foodcab

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import id.filkom.mat.foodcab.R.id.login_form
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
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


    //This is Register
    @Test
    fun validPasswordWrongRetype(){
        Espresso.onView(ViewMatchers.withId(R.id.text_register)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("hola@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.uname)).perform(ViewActions.typeText("holaf"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("holinda"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_up_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("This password not match")))
    }

    @Test
    fun validPasswordRightRetype(){
        Espresso.onView(ViewMatchers.withId(R.id.text_register)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("hola@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.uname)).perform(ViewActions.typeText("holaf"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_up_button)).perform(ViewActions.click())
    }

    @Test
    fun validUsername(){
        Espresso.onView(ViewMatchers.withId(R.id.text_register)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("holac@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.uname)).perform(ViewActions.typeText("holaf"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_up_button)).perform(ViewActions.click())
    }

    @Test
    fun invalidUsername(){
        Espresso.onView(ViewMatchers.withId(R.id.text_register)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.email)).perform(ViewActions.typeText("hola@mail.you"))
        Espresso.onView(ViewMatchers.withId(R.id.uname)).perform(ViewActions.typeText("holaf"))
        Espresso.onView(ViewMatchers.withId(R.id.password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.confirm_password)).perform(ViewActions.typeText("holanda"))
        Espresso.onView(ViewMatchers.withId(R.id.email_sign_up_button)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.uname)).check(ViewAssertions.matches(ViewMatchers.hasErrorText("Username terlalu pendek")))

    }

}