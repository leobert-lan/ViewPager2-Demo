package com.example.viewpager2demo.nested;

import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * <p>a scroll listener that detects RecyclerView scroll to end to load more data </p>
 * <p>use  {@link RecyclerView#addOnScrollListener(RecyclerView.OnScrollListener)}</p>
 * Created by calvin on 16/12/1.
 */
@SuppressWarnings("WeakerAccess")
public abstract class OnReachBottomListener extends RecyclerView.OnScrollListener {

    private static final int THRESHOLD = 3;

    /**
     * 当前RecyclerView类型
     */
    @LayoutManagerType
    protected int layoutManagerType = LayoutManagerType.NOT_SUPPORT;

    /**
     * 最后一个的位置
     */
    private int[] lastPositions;

    /**
     * 最后一个可见的item的位置
     */
    private int lastVisibleItemPosition;


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) return;

        if (layoutManager instanceof LinearLayoutManager) {// GridLayoutManager is child of LinearLayoutManager
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (lastPositions == null) {
                lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
            }
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
            lastVisibleItemPosition = findMax(lastPositions);
        } else {
            throw new RuntimeException(
                    "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
        }


//        if (layoutManagerType == LayoutManagerType.NOT_SUPPORT) {
//
//            if (layoutManager instanceof GridLayoutManager) {
//                layoutManagerType = LayoutManagerType.GridLayout;
//            } else if (layoutManager instanceof LinearLayoutManager) {
//                layoutManagerType = LayoutManagerType.LinearLayout;
//            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
//                layoutManagerType = LayoutManagerType.StaggeredGridLayout;
//            } else {
//                throw new RuntimeException(
//                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
//            }
//        }
//
//        switch (layoutManagerType) {
//            case LayoutManagerType.LinearLayout:
////                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() - THRESHOLD;
//                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
//                break;
//            case LayoutManagerType.GridLayout:
//                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
//                break;
//            case LayoutManagerType.StaggeredGridLayout:
//                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
//                if (lastPositions == null) {
//                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
//                }
//                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
//                lastVisibleItemPosition = findMax(lastPositions);
//                break;
////            case LayoutManagerType.NOT_SUPPORT: unreachable
//            default:
//                break;
//        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);


        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        //没有 layoutManager 根本没法布局的
        if (layoutManager == null) return;

        int visibleItemCount = layoutManager.getChildCount();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        int totalItemCount = layoutManager.getItemCount();
        if (adapter != null)
            totalItemCount = adapter.getItemCount();

        Log.d("RecyclerView", "onScrollStateChanged--lastVisibleItemPosition->" + lastVisibleItemPosition + " totalItemCount==》" + totalItemCount);
        if ((visibleItemCount > 0 && newState != RecyclerView.SCROLL_STATE_DRAGGING && (lastVisibleItemPosition) >= totalItemCount - 1 - THRESHOLD)) {
            Log.d("RecyclerView", "ReachBottom--lastVisibleItemPosition->" + lastVisibleItemPosition);
            onReachBottom(recyclerView);
        }
    }

    /**
     * 取数组中最大值
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    public abstract void onReachBottom(final RecyclerView recyclerView);

    @IntDef({LayoutManagerType.LinearLayout, LayoutManagerType.StaggeredGridLayout, LayoutManagerType.GridLayout, LayoutManagerType.NOT_SUPPORT})
    public @interface LayoutManagerType {
        //        LinearLayout,
//        StaggeredGridLayout,
//        GridLayout
        int LinearLayout = 1;
        int StaggeredGridLayout = 2;
        int GridLayout = 3;
        int NOT_SUPPORT = 4;
    }

}
