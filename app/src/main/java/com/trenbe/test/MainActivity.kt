package com.trenbe.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.trenbe.test.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.util.DisplayMetrics



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val metrics = resources.displayMetrics

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        println("density = $metrics")
        view_pager.adapter = sectionsPagerAdapter
        tabs.run {
            setupWithViewPager(view_pager)
            getTabAt(2)?.select()
        }
    }
}