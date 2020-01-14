package com.example.viewpager2demo.nested

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.viewpager2demo.R
import com.example.viewpager2demo.base.adapter.vh2.AbsViewHolder2
import com.example.viewpager2demo.base.adapter.vh2.DataBindingViewHolder
import com.example.viewpager2demo.databinding.AppVhBarBinding
import osp.leobert.android.pandora.rv.DataSet
import osp.leobert.android.pandora.rv.ViewHolderCreator

interface BarVO2 : DataSet.Data<BarVO2, AbsViewHolder2<BarVO2>> {
    override fun setToViewHolder(viewHolder: AbsViewHolder2<BarVO2>?) {
        viewHolder?.setData(this)
    }

    class Impl : BarVO2
}

class BarVHCreator(private val itemInteract: BarItemInteract?) : ViewHolderCreator() {

    override fun createViewHolder(parent: ViewGroup): DataBindingViewHolder<BarVO2, AppVhBarBinding> {
        val binding = DataBindingUtil.inflate<AppVhBarBinding>(
                LayoutInflater.from(parent.context),
                R.layout.app_vh_bar, parent, false
        )

        val vh = object : DataBindingViewHolder<BarVO2, AppVhBarBinding>(binding) {

            var mData: BarVO2? = null

            override fun setData(data: BarVO2?) {
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


interface BarItemInteract {
    //TODO: define interact functions here
}


