package com.example.viewpager2demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
                getFg().show(getSupportFragmentManager(),"fg");
                getFg().show(getSupportFragmentManager(),"fg");
//                NestedTestActivity2.Companion.start(view.getContext());
            }
        });
    }

    private Fg getFg() {
        if (fg == null)
            fg = new Fg();
        return fg;
    }
    private Fg fg;

    class Fg extends DialogFragmentFixed {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            try {
                getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return inflater.inflate(R.layout.tmp, container);
        }
    }
}
