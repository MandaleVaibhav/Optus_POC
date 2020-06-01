package com.example.optuspoc

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LandingScreenActivityTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LandingScreenActivity::class.java)

    @Test
    fun userInformationTest() {
        Thread.sleep(5000)

        val textView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.id), ViewMatchers.withText("ID: 1"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView.check(ViewAssertions.matches(ViewMatchers.withText("ID: 1")))

        val textView2 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.tvName), ViewMatchers.withText("Name: Leanne Graham"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView2.check(ViewAssertions.matches(ViewMatchers.withText("Name: Leanne Graham")))

        val textView3 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.tvEmail),
                ViewMatchers.withText("email: Sincere@april.biz"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView3.check(ViewAssertions.matches(ViewMatchers.withText("email: Sincere@april.biz")))

        val textView4 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.phone),
                ViewMatchers.withText("phone: 1-770-736-8031 x56442"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView4.check(ViewAssertions.matches(ViewMatchers.withText("phone: 1-770-736-8031 x56442")))

        val textView5 = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.phone),
                ViewMatchers.withText("phone: 1-770-736-8031 x56442"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(FrameLayout::class.java),
                        0
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textView5.check(ViewAssertions.matches(ViewMatchers.withText("phone: 1-770-736-8031 x56442")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}