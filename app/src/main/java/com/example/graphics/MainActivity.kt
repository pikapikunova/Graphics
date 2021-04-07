package com.example.graphics

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphics.ui.main.MainFragment
import com.example.graphics.ui.main.start_page

class MainActivity : AppCompatActivity() {

    lateinit var prefs: SharedPreferences
    lateinit var cont: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.startPage, start_page.newInstance())
                    .commitNow()
        }

        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE)

    }

    fun openMainFragment()
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

    fun openStartFragment()
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