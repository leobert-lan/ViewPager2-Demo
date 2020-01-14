package com.example.viewpager2demo.base.adapter.vh2;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import osp.leobert.android.pandora.rv.IViewHolder;

/**
 * <p><b>Package:</b> com.jdd.motorfans.common.base.adapter.vh2 </p>
 * <p><b>Project:</b> MotorFans </p>
 * <p><b>Classname:</b> AbsViewHolder2 </p>
 *
 * Created by leobert on 2018/10/16.
 */
public abstract class AbsViewHolder2<T> extends RecyclerView.ViewHolder implements IViewHolder<T> {
    public AbsViewHolder2(View itemView) {
        super(itemView);
        bindViews();
    }

    protected void bindViews() {
    }

    @Override
    public RecyclerView.ViewHolder asViewHolder() {
        return this;
    }

    public Context getContext() {
        return itemView.getContext();
    }


    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }

    @Override
    public void accept(@NonNull Visitor visitor) {
    }
}
