package com.example.viewpager2demo.nested

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2demo.R
import com.example.viewpager2demo.base.adapter.vh2.RvAdapter2
import kotlinx.android.synthetic.main.activity_nested_test.*
import osp.leobert.android.pandora.Pandora
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.PandoraRealRvDataSet

/**
 * <p><b>Package:</b> com.example.viewpager2demo.nested </p>
 * <p><b>Classname:</b> RvFragment </p>
 * Created by leobert on 2020-01-14.
 */
class RvFragment : Fragment() {
    companion object {
        fun newInstance(): RvFragment {
            return RvFragment()
        }
    }


    override fun onResume() {
        super.onResume()
        Log.e("lmsg", "onResume:" + javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        Log.e("lmsg", "onPause:" + javaClass.simpleName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_nested_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
            Log.d("lmsg", "onStateChanged: RvFragment $event")
        })

        val dataset: PandoraRealRvDataSet<DataSet.Data<*, *>> = PandoraRealRvDataSet<DataSet.Data<*, *>>(Pandora.real())

        val adapter = RvAdapter2<PandoraRealRvDataSet<DataSet.Data<*, *>>>(dataset)

        Pandora.bind2RecyclerViewAdapter(dataset.getRealDataSet(), adapter)

        rv.layoutManager = LinearLayoutManager(rv.context)
        rv.adapter = adapter

        rv.isNestedScrollingEnabled = true

        dataset.registerDVRelation(FooVO2.Impl::class.java, FooVHCreator(null))
        dataset.registerDVRelation(BarVO2.Impl::class.java, BarVHCreator(null))
        dataset.registerDVRelation(TabVO2.Impl::class.java, TabVHCreator(null))

        dataset.startTransaction()
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.add(BarVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.add(FooVO2.Impl())
        dataset.endTransaction()

        rv.addOnScrollListener(object : OnReachBottomListener() {
            override fun onReachBottom(recyclerView: RecyclerView?) {
                Toast.makeText(rv.context, "bottom", Toast.LENGTH_SHORT).show()
            }
        }.apply {
            this.layoutManagerType = OnReachBottomListener.LayoutManagerType.LinearLayout
        })
    }
}