/*
 * MIT License
 *
 * Copyright (c) 2018 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.example.viewpager2demo.base.adapter.vh2;

import android.util.Log;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import osp.leobert.android.pandora.Logger;
import osp.leobert.android.pandora.PandoraException;
import osp.leobert.android.pandora.rv.DataObserver;
import osp.leobert.android.pandora.rv.DataSet;

/**
 * <p><b>Package:</b> osp.leobert.android.pandorasample </p>
 * <p><b>Project:</b> Pandora </p>
 * <p><b>Classname:</b> RvAdapter </p>
 * <p><b>Description:</b> it is just a demo, you can just use it in your project or copy
 * the core code into your Adapter. Considering the existing Adapters about something updateLike "pull-to-refresh",
 * "load-more" provided by some libraries used in your app, it's much more enjoyable to use a Composition pattern</p>
 * Created by leobert on 2018/10/11.
 */
public class RvAdapter2<D extends DataSet> extends RecyclerView.Adapter<AbsViewHolder2>
        implements DataObserver {

    private final D dataSet;
    private String tag = "not set";

    protected D dataSet() {
        return dataSet;
    }

    public RvAdapter2(D dataSet) {
        this.dataSet = dataSet;
        dataSet.addDataObserver(this);
    }

    public RvAdapter2(D dataSet, String tag) {
        this.dataSet = dataSet;
        dataSet.addDataObserver(this);
        this.tag = tag;
    }

    @Override
    public AbsViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            return (AbsViewHolder2) dataSet.createViewHolderV2(parent, viewType);
        } catch (PandoraException e) {
            e.printStackTrace();
            Logger.e(Logger.TAG, tag, e);
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NotNull AbsViewHolder2 holder, int position) {
        Log.i("Pandora", "onBindViewHolder: " + position);
        try {
            DataSet.helpSetToViewHolder(dataSet.getItem(position), holder);
//            dataSet.getItem(position).setToViewHolder(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return dataSet.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        try {
            return dataSet.getItemViewTypeV2(position);
        } catch (PandoraException e) {
            e.printStackTrace();
            Logger.e(Logger.TAG, tag, e);
            return -1;
        }
    }

    @Override
    public void onViewAttachedToWindow(@NotNull AbsViewHolder2 holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
    }


    @Override
    public void onViewDetachedFromWindow(@NotNull AbsViewHolder2 holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    @Override
    public void onDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
