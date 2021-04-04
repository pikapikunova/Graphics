package com.example.graphics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.graphics.ui.main.MainFragment
import com.example.graphics.ui.main.start_page
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.start_page_fragment.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.startPage, start_page.newInstance())
                    .commitNow()
        }

    }

    public fun openMainFragment()
    {

        var fragment = supportFragmentManager.findFragmentById (R.id.cartesianView);
        val fragmentStart = supportFragmentManager.findFragmentById (R.id.startPage);


        if (fragment == null) {
            fragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.secondFragment, fragment)
                .replace(R.id.secondFragment, MainFragment.newInstance())
                .hide(fragmentStart!!)
                .commitNow()

        }
    }

    public fun openStartFragment()
    {
        var fragment = supportFragmentManager.findFragmentById (R.id.startoff);
        val fragmentMain = supportFragmentManager.findFragmentById (R.id.secondFragment);


        if (fragment == null) {
            fragment = start_page()
            supportFragmentManager.beginTransaction()
                .add(R.id.startPage, fragment)
                .replace(R.id.startPage, start_page.newInstance())
                .hide(fragmentMain!!)
                .commitNow()

        }
    }





}