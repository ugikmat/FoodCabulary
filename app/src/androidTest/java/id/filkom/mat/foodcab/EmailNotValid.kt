package id.filkom.mat.foodcab


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
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



    @Test
    fun emailNotValid(){
        onView(withId(R.id.email))        // withId(R.id.my_view) is a ViewMatcher
        .perform(typeText("Hello"), click());

        onView(withId(R.id.password))        // withId(R.id.my_view) is a ViewMatcher
                .perform(typeText("Hello"), click());
        onView(withId(R.id.email_sign_in_button))        // withId(R.id.my_view) is a ViewMatcher
                .perform(click());
    }

}
