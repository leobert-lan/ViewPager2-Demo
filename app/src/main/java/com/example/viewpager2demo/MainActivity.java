package com.example.viewpager2demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnHorizontalScrolling = findViewById(R.id.btn_horizontal_scrolling);
        btnHorizontalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HorizontalScrollingActivity.start(MainActivity.this);
            }
        });
        Button btnVerticalScrolling = findViewById(R.id.button_vertical_scrolling);
        btnVerticalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerticalScrollingActivity.start(MainActivity.this);
            }
        });
        Button btnFragmentStateAdapter = findViewById(R.id.button_fragment_state_adpater);
        btnFragmentStateAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentStateAdapterActivity.start(MainActivity.this);
            }
        });


        findViewById(R.id.nest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NestedTestActivity.Companion.start(view.getContext());
            }
        });
    }
}
