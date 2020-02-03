package com.example.viewpager2demo.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class LogCoordinatorLayout extends CoordinatorLayout {
    public LogCoordinatorLayout(@NonNull Context context) {
        super(context);
    }

    public LogCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LogCoordinatorLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private String logTag() {
        Object t = getTag();
        if (t != null)
            return t.toString();
        return "coco";
    }

    @Override
    public boolean startNestedScroll(int axes) {
        Log.d(logTag(),"startNestedScroll 1");
        return super.startNestedScroll(axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d(logTag(),"[p] onStartNestedScroll 1");
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        Log.d(logTag(),"[p] onStartNestedScroll 2");
        return super.onStartNestedScroll(child, target, axes, type);
    }

    //    @Override
//    public boolean startNestedScroll(int axes, int type) {
//        Log.d(logTag(),"startNestedScroll 2");
//        return super.startNestedScroll(axes, type);
//    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        Log.d(logTag(),"dispatchNestedPreScroll 1");
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

//    @Override
//    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow, int type) {
//        Log.d(logTag(),"dispatchNestedPreScroll 2");
//        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
//    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        Log.d(logTag(),"dispatchNestedScroll 1");
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

//    @Override
//    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow, int type) {
//        Log.d(logTag(),"dispatchNestedScroll 2");
//        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
//    }

    @Override
    public void stopNestedScroll() {
        Log.d(logTag(),"stopNestedScroll 1");
        super.stopNestedScroll();
    }

//    @Override
//    public void stopNestedScroll(int type) {
//        Log.d(logTag(),"stopNestedScroll 2");
//        super.stopNestedScroll(type);
//    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        Log.d(logTag(),"onNestedPreFling");
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(logTag(),"onNestedFling");
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        Log.d(logTag(),"dispatchNestedPreFling");
        return super.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        Log.d(logTag(),"dispatchNestedFling");
        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }
}
