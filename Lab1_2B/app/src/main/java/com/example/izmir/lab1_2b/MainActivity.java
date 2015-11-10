package com.example.izmir.lab1_2b;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(android.os.Build.VERSION.SDK_INT >= 21) {
    this.notificationBar();
    }
        //Create layout

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);

        GridLayout myLayout = new GridLayout(this);
        myLayout.setColumnCount(2);
        myLayout.setRowCount(4);
        myLayout.setUseDefaultMargins(true);
        GridLayout.Spec row1 = GridLayout.spec(0);
        GridLayout.Spec row2 = GridLayout.spec(1);
        GridLayout.Spec row3 = GridLayout.spec(2);
        GridLayout.Spec row4 = GridLayout.spec(3);
        GridLayout.Spec col1 = GridLayout.spec(0);
        GridLayout.Spec col2 = GridLayout.spec(1);

        //Create toolbar

        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Lab1_2B");
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));


        //Create TextViews
        TextView Name, Password, Email, Age;
        Name = new TextView(this);
        Password = new TextView(this);
        Email = new TextView(this);
        Age = new TextView(this);

        Name.setText("Namn");
        Password.setText("Lösenord  ");
        Email.setText("Epost");
        Age.setText("Ålder");

        //Create EditTexts

        EditText theName = new EditText(this);
        theName.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        theName.setText("SomeName");
        theName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        EditText thePassword = new EditText(this);
        thePassword.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        thePassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        EditText theEmail = new EditText(this);
        theEmail.setWidth(GridLayout.LayoutParams.MATCH_PARENT);
        theEmail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //Create seekbar
        SeekBar seek = new SeekBar(this);

        //Create spec
        GridLayout.LayoutParams first = new GridLayout.LayoutParams(row1,col1);
        first.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams second = new GridLayout.LayoutParams(row1,col2);
        second.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams third = new GridLayout.LayoutParams(row2,col1);
        third.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams fourth = new GridLayout.LayoutParams(row2,col2);
        fourth.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams fifth = new GridLayout.LayoutParams(row3,col1);
        fifth.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams sixth = new GridLayout.LayoutParams(row3,col2);
        sixth.width = GridLayout.LayoutParams.MATCH_PARENT;

        GridLayout.LayoutParams seventh = new GridLayout.LayoutParams(row4,col1);
        seventh.width = GridLayout.LayoutParams.WRAP_CONTENT;
        GridLayout.LayoutParams eighth = new GridLayout.LayoutParams(row4,col2);
        eighth.width = GridLayout.LayoutParams.MATCH_PARENT;


        //Add components to layout

        myLayout.addView(Name,first);
        myLayout.addView(theName,second);
        myLayout.addView(Password,third);
        myLayout.addView(thePassword,fourth);
        myLayout.addView(Email,fifth);
        myLayout.addView(theEmail,sixth);
        myLayout.addView(Age, seventh);
        myLayout.addView(seek,eighth);


        linLayout.addView(toolbar);
        linLayout.addView(myLayout);

        setContentView(linLayout);

    }
    //Change color of top bar but only if api version 21 or higher.
    @TargetApi(21)
    private void notificationBar(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
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
