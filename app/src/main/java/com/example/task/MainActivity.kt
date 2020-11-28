package com.example.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

lateinit var tabLayout:TabLayout
lateinit var tabViewPager:ViewPager2
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout  = findViewById(R.id.tab)
        tabViewPager = findViewById(R.id.vp)



        tabViewPager.adapter=object : FragmentStateAdapter(this){

            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0 -> one_Fragment.newInstance("","")
                    1 -> two_Fragment.newInstance("s","s1")
                    2 -> three_Fragment.newInstance("","")
                    else -> one_Fragment.newInstance("","")
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }

        TabLayoutMediator(tabLayout, tabViewPager){tab, position ->
            tab.text = when(position){
                0 -> "ToDo"
                1 -> "In Progress"
                2 -> "Done"
                else -> null
            }
        }.attach()
    }
}