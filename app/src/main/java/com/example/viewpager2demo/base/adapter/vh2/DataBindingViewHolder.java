package com.example.viewpager2demo.base.adapter.vh2;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p><b>Package:</b> com.example.myapplication </p>
 * <p><b>Project:</b> MyApplication </p>
 * <p><b>Classname:</b> AbsViewHolder2 </p>
 *
 * Created by leobert on 2019-06-03.
 */
public abstract class DataBindingViewHolder<T,VDB extends ViewDataBinding> extends AbsViewHolder2<T> {
    public DataBindingViewHolder(VDB viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    @Override
    protected void bindViews() {
    }

    private final VDB viewDataBinding;

    @Override
    public RecyclerView.ViewHolder asViewHolder() {
        return this;
    }

    @Override
    public void setData(T data) {
    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }
}
