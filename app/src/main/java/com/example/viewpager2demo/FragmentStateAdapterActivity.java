package com.example.viewpager2demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.viewpager2demo.nested.RvFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * leobert
 */
public class FragmentStateAdapterActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private List<Integer> colors = new ArrayList<>();
    private ViewPagerFragmentStateAdapter mAdapter;

    private static final String key_index = "key_index";

    {
        colors.add(android.R.color.black);
        colors.add(android.R.color.holo_purple);
        colors.add(android.R.color.holo_blue_dark);
        colors.add(android.R.color.holo_green_light);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentStateAdapterActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                if (TextUtils.equals(item.getTitle(), getString(R.string.action_add))) {
                    colors.add(android.R.color.holo_red_light);
                    mTabLayout.addTab(mTabLayout.newTab().setText("Tab4"));
                    mAdapter.notifyItemInserted(colors.size() - 1);
                    item.setIcon(R.drawable.ic_action_remove);
                    item.setTitle(R.string.action_remove);
                } else {
                    item.setIcon(R.drawable.ic_action_add);
                    item.setTitle(R.string.action_add);
                    int last = colors.size() - 1;
                    colors.remove(last);
                    mTabLayout.removeTabAt(last);
                    mAdapter.notifyItemRemoved(last);
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_state_adapter);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager2 = findViewById(R.id.viewpager2);

//        if (mViewPager2)
//        mViewPager2.setOffscreenPageLimit(0);

        mAdapter = new ViewPagerFragmentStateAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager2.setAdapter(mAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab-rv"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab0"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab3"));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("lmsg", "switch page:" + tab.getPosition());
                mViewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("lmsg", "onTabReselected:" + tab.getPosition());
                if (mViewPager2.getCurrentItem() != tab.getPosition()) {
                    mViewPager2.setCurrentItem(tab.getPosition());
                }
            }
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position, 0, false);
            }
        });

//        if (savedInstanceState != null) {
//            int index = savedInstanceState.getInt(key_index);
//            mTabLayout.setScrollPosition(index, 0, true);
//        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int index = savedInstanceState.getInt(key_index);
        mTabLayout.setScrollPosition(index, 0, true);
        mViewPager2.setCurrentItem(index);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(key_index, mTabLayout.getSelectedTabPosition());
        super.onSaveInstanceState(outState, outPersistentState);
    }

    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {
        public ViewPagerFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public ViewPagerFragmentStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0)
                return RvFragment.Companion.newInstance();
            else
                return PageFragment.newInstance(colors, position - 1);
        }

        @Override
        public int getItemCount() {
            return colors.size() + 1;
        }

    }


}
