package com.example.viewpager2demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2demo.base.adapter.vh2.RvAdapter2
import com.example.viewpager2demo.nested.*
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_nested_test.rv
import kotlinx.android.synthetic.main.activity_nested_test2.*
import osp.leobert.android.pandora.Pandora
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraRealRvDataSet
import java.util.*

class NestedTestActivity2 : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_nested_test2)
//    }

    companion object {
        fun start(context: Context) {
            Intent(context, NestedTestActivity2::class.java).let {
                context.startActivity(it)
            }
        }
    }

    val dataset: PandoraRealRvDataSet<DataSet.Data<*, *>> = PandoraRealRvDataSet<DataSet.Data<*, *>>(Pandora.real())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_test2)

        val adapter = RvAdapter2<PandoraRealRvDataSet<DataSet.Data<*, *>>>(dataset)

        Pandora.bind2RecyclerViewAdapter(dataset.getRealDataSet(), adapter)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        rv.isNestedScrollingEnabled = false

        rv.addOnScrollListener(object :RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.d("rvrv", "onScrolled")
                super.onScrolled(recyclerView, dx, dy)
            }
        })


        dataset.registerDVRelation(FooVO2.Impl::class.java, FooVHCreator(null))
        dataset.registerDVRelation(BarVO2.Impl::class.java, BarVHCreator(null))
//        dataset.registerDVRelation(TabVO2.Impl::class.java, TabVHCreator(null))

        dataset.startTransaction()
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(BarVO2.Impl())

        //增加长度看是否有滚动
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.endTransaction()

        tmp()

    }


    private fun tmp() {
        val colors = ArrayList<Int>()

        colors.add(android.R.color.black)
        colors.add(android.R.color.holo_purple)
        colors.add(android.R.color.holo_blue_dark)
        colors.add(android.R.color.holo_green_light)


        val mAdapter = ViewPagerFragmentStateAdapter(colors, supportFragmentManager, lifecycle)
        viewpager2.adapter = mAdapter
        tablayout.addTab(tablayout.newTab().setText("Tab-rv"))
        tablayout.addTab(tablayout.newTab().setText("Tab0"))
        tablayout.addTab(tablayout.newTab().setText("Tab1"))
        tablayout.addTab(tablayout.newTab().setText("Tab2"))
        tablayout.addTab(tablayout.newTab().setText("Tab3"))
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e("lmsg", "switch page:" + tab.position)
                viewpager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                Log.e("lmsg", "onTabReselected:" + tab.position)
                if (viewpager2.currentItem != tab.position) {
                    viewpager2.currentItem = tab.position
                }
            }
        })
        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tablayout.setScrollPosition(position, 0f, false)
            }
        })
    }

//            viewpager2.isNestedScrollingEnabled = false
//
//            viewpager2.getChildAt(0)?.takeIfInstance<RecyclerView>()?.let {
//                it.isNestedScrollingEnabled = false
//            }
}
