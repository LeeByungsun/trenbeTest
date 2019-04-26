package com.trenbe.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.trenbe.test.TestApplication.Companion.density
import com.trenbe.test.ui.main.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val metrics = resources.displayMetrics

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        density = metrics.density
        println("density = $metrics")
        view_pager.adapter = sectionsPagerAdapter
        tabs.run {
            setupWithViewPager(view_pager)
            getTabAt(2)?.select()
        }
    }
}