package id.filkom.mat.foodcab


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.squareup.javawriter.JavaWriter.type
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by mat on 5/14/18.
 */
@RunWith(AndroidJUnit4::class)
class EmailNotValid {


    @get: Rule
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun invalidEmail() {
        //to check view on screen
        onView(withId(R.id.email)).perform(typeText("Halo Semuanya"))
        onView(withId(R.id.password)).perform(typeText("Halo emuanya"))
        onView(withId(R.id.email_sign_in_button)).perform(click())
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    fun emptyEmail() {
        //to check view on screen
        onView(withId(R.id.password)).perform(typeText("Halo emuanya"))
        onView(withId(R.id.email_sign_in_button)).perform(click())
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}
