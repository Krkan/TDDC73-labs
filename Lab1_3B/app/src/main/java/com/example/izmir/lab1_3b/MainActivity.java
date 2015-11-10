package com.example.izmir.lab1_3b;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //Create the toolbar
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Lab1_3B");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.inflateMenu(R.menu.menu_main);


        //Create TextView
        TextView tView1 = new TextView(this);
        tView1.setText("Hur trivs du på LiU?");
        tView1.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView tView2 = new TextView(this);
        tView2.setText("Läser du på LiTH?");
        tView2.setGravity(Gravity.CENTER_HORIZONTAL);

        //Create LinearLayouts for checkboxes
        LinearLayout cl = new LinearLayout(this);
        cl.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout cl2 = new LinearLayout(this);
        cl2.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout cl3 = new LinearLayout(this);
        cl3.setOrientation(LinearLayout.HORIZONTAL);

        //Create checkboxes
        CheckBox box1 = new CheckBox(this);
        box1.setText("Bra");


        CheckBox box2 = new CheckBox(this);
        box2.setText("Mycket bra");


        CheckBox box3 = new CheckBox(this);
        box3.setText("Jättebra");
        box3.setChecked(true);

        CheckBox box4 = new CheckBox(this);
        box4.setText("Ja");
        box4.setChecked(true);


        CheckBox box5 = new CheckBox(this);
        box5.setText("Nej");

        CheckBox box6 = new CheckBox(this);
        box6.setText("Ja");
        box6.setChecked(true);


        CheckBox box7 = new CheckBox(this);
        box7.setText("Nej");

        //Create ImageView
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.index);

        //Create button
        Button btn = new Button(this);
        btn.setText("SKICKA IN");


        //Add boxes to their layouts
        cl.addView(box1);
        cl.addView(box2);
        cl.addView(box3);

        cl2.addView(box4);
        cl2.addView(box5);

        cl3.addView(box6);
        cl3.addView(box7);


        //Add components to main layout
        linearLayout.addView(toolbar);
        linearLayout.addView(tView1);
        linearLayout.addView(cl);
        linearLayout.addView(tView2);
        linearLayout.addView(cl2);
        linearLayout.addView(image);
        linearLayout.addView(cl3);
        linearLayout.addView(btn);
        setContentView(linearLayout);

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
