package com.example.viewpager2demo.nested

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2demo.PageFragment
import com.example.viewpager2demo.R
import com.example.viewpager2demo.base.adapter.vh2.AbsViewHolder2
import com.example.viewpager2demo.base.adapter.vh2.DataBindingViewHolder
import com.example.viewpager2demo.databinding.AppVhTabBinding
import com.google.android.material.tabs.TabLayout
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.ViewHolderCreator
import java.util.*

interface TabVO2 : DataSet.Data<TabVO2, AbsViewHolder2<TabVO2>> {
    override fun setToViewHolder(viewHolder: AbsViewHolder2<TabVO2>?) {
        viewHolder?.setData(this)
    }

    class Impl : TabVO2
}

public inline fun <reified R> Any?.takeIfInstance(): R? {
    if (this is R) return this
    return null
}

class TabVHCreator(private val itemInteract: TabItemInteract?) : ViewHolderCreator() {

    override fun createViewHolder(parent: ViewGroup): DataBindingViewHolder<TabVO2, AppVhTabBinding> {
        val binding = DataBindingUtil.inflate<AppVhTabBinding>(
                LayoutInflater.from(parent.context),
                R.layout.app_vh_tab, parent, false
        )

        val vh = object : DataBindingViewHolder<TabVO2, AppVhTabBinding>(binding) {

            var mData: TabVO2? = null
            lateinit var mAdapter: ViewPagerFragmentStateAdapter
            private val colors = ArrayList<Int>()

            init {
                colors.add(android.R.color.black)
                colors.add(android.R.color.holo_purple)
                colors.add(android.R.color.holo_blue_dark)
                colors.add(android.R.color.holo_green_light)

                context.takeIfInstance<FragmentActivity>()?.let {

                    mAdapter = ViewPagerFragmentStateAdapter(colors, it.supportFragmentManager, it.lifecycle)
                    binding.viewpager2.adapter = mAdapter
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab-rv"))
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab0"))
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab1"))
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab2"))
                    binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tab3"))
                    binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab) {
                            Log.e("lmsg", "switch page:" + tab.position)
                            binding.viewpager2.setCurrentItem(tab.position)
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab) {

                        }

                        override fun onTabReselected(tab: TabLayout.Tab) {
                            Log.e("lmsg", "onTabReselected:" + tab.position)
                        }
                    })
                    binding.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            binding.tabLayout.setScrollPosition(position, 0f, false)
                        }
                    })
                }

                binding.viewpager2.isNestedScrollingEnabled = true

                binding.viewpager2.getChildAt(0)?.takeIfInstance<RecyclerView>()?.let {
                    it.isNestedScrollingEnabled = true
                }

            }

            override fun setData(data: TabVO2?) {
                super.setData(data)
                mData = data
                binding.vo = data
                binding.executePendingBindings()
            }
        }

        binding.vh = vh
        binding.itemInteract = itemInteract

        return vh
    }
}

class ViewPagerFragmentStateAdapter : FragmentStateAdapter {

    val colors: MutableList<Int>

    constructor(colors: MutableList<Int>, fragmentActivity: FragmentActivity) : super(fragmentActivity) {
        this.colors = colors
    }

    constructor(colors: MutableList<Int>, fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(fragmentManager, lifecycle) {
        this.colors = colors
    }

    constructor(colors: MutableList<Int>, fragment: Fragment) : super(fragment) {
        this.colors = colors
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            RvFragment.newInstance()
        else
            PageFragment.newInstance(colors, position - 1)
    }

    override fun getItemCount(): Int {
        return colors.size + 1
    }

}


interface TabItemInteract {
}


