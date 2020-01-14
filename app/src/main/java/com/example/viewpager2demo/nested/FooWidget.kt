package com.example.viewpager2demo.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.viewpager2demo.R
import com.example.viewpager2demo.base.adapter.vh2.AbsViewHolder2
import com.example.viewpager2demo.base.adapter.vh2.DataBindingViewHolder
import com.example.viewpager2demo.databinding.AppVhFooBinding
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.ViewHolderCreator

interface FooVO2 : DataSet.Data<FooVO2, AbsViewHolder2<FooVO2>> {
    override fun setToViewHolder(viewHolder: AbsViewHolder2<FooVO2>?) {
        viewHolder?.setData(this)
    }

    class Impl : FooVO2
}

class FooVHCreator(private val itemInteract: FooItemInteract?) : ViewHolderCreator() {

    override fun createViewHolder(parent: ViewGroup): DataBindingViewHolder<FooVO2, AppVhFooBinding> {
        val binding = DataBindingUtil.inflate<AppVhFooBinding>(
                LayoutInflater.from(parent.context),
                R.layout.app_vh_foo, parent, false
        )

        val vh = object : DataBindingViewHolder<FooVO2, AppVhFooBinding>(binding) {

            var mData: FooVO2? = null

            init {
                binding.root.setOnClickListener {
                    Toast.makeText(it.context, "Foo", Toast.LENGTH_SHORT).show()
                }
            }

            override fun setData(data: FooVO2?) {
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


interface FooItemInteract {
}


