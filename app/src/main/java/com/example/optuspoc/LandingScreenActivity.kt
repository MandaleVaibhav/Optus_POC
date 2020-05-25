package com.example.optuspoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.optuspoc.view.UserInformationFragment

class LandingScreenActivity : AppCompatActivity() {

    var tag: String = UserInformationFragment::class.java.name
    var name = UserInformationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_screen)
        showFragment(UserInformationFragment(), tag)
    }

    /**
     * Calling fragment
     * */
    fun showFragment(name: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped) {
            val fragmentTransaction =
                fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, name, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }
}
