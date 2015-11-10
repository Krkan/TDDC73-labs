package com.example.izmir.lab1_1b;

import android.app.ActionBar;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        //Create the toolbar
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Lab1_1B");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.inflateMenu(R.menu.menu_main);

        //Create button
        Button myButton = new Button(this);
        myButton.setText("Knapp");
        myButton.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        //Create an edit text
        EditText etext = new EditText(this);
        etext.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        etext.setText("Text fält");
        etext.setTextSize(20);

        //Create rating bar, must be wraped
        RatingBar rb =new  RatingBar(this);
        rb.setNumStars(5);
        rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        //Create TextView
        TextView txt = new TextView(this);
        txt.setText("Text fält \nmed flera\nrader.");
        txt.setTextSize(20);

        //Create layout
        LinearLayout myLayout = new LinearLayout(this);
        myLayout.setOrientation(LinearLayout.VERTICAL);
        myLayout.setGravity(Gravity.CENTER | Gravity.TOP);

        //Add all components to layout.
        myLayout.addView(toolbar);
        myLayout.addView(myButton);
        myLayout.addView(etext);
        myLayout.addView(rb);
        myLayout.addView(txt);
        setContentView(myLayout);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
