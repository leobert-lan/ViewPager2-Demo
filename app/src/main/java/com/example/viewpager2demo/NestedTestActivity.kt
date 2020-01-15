package com.example.viewpager2demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpager2demo.base.adapter.vh2.RvAdapter2
import com.example.viewpager2demo.nested.*
import kotlinx.android.synthetic.main.activity_nested_test.*
import osp.leobert.android.pandora.Pandora
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraRealRvDataSet

class NestedTestActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            Intent(context, NestedTestActivity::class.java).let {
                context.startActivity(it)
            }
        }
    }

    val dataset: PandoraRealRvDataSet<DataSet.Data<*, *>> = PandoraRealRvDataSet<DataSet.Data<*, *>>(Pandora.real())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_test)

        val adapter = RvAdapter2<PandoraRealRvDataSet<DataSet.Data<*, *>>>(dataset)

        Pandora.bind2RecyclerViewAdapter(dataset.getRealDataSet(), adapter)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        rv.isNestedScrollingEnabled = false
//        rv.suppressLayout(true)


        dataset.registerDVRelation(FooVO2.Impl::class.java, FooVHCreator(null))
        dataset.registerDVRelation(BarVO2.Impl::class.java, BarVHCreator(null))
        dataset.registerDVRelation(TabVO2.Impl::class.java, TabVHCreator(null))

        dataset.startTransaction()
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.add(TabVO2.Impl())
        dataset.endTransaction()

    }
}
