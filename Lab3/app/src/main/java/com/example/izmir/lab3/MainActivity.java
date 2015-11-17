package com.example.izmir.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        InteractiveSearcher s = new InteractiveSearcher(this);
        s.setNrResults(6);
        s.setMinSearchLength(2);
        layout.addView(s);
        setContentView(layout);
    }
}
