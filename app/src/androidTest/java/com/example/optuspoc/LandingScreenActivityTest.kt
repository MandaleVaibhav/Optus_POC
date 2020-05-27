package com.example.optuspoc

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.optuspoc.view.UserInformationFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LandingScreenActivityTest {
    @Rule @JvmField var activityActivityTestRule = ActivityTestRule(LandingScreenActivity::class.java)

    /* Test to check fragment */
    @Test
    fun testDynamicFragment() {
        val fragment = UserInformationFragment()
        activityActivityTestRule.activity.supportFragmentManager.beginTransaction()
            .add(R.id.landing_fragment_container, fragment).commit()
    }
}