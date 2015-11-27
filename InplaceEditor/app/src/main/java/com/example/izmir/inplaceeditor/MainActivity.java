
/*Test program for the InplaceEditor
* */

package com.example.izmir.inplaceeditor;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    InplaceEditor ie;
    TextView dummy;
    InplaceEditor ie2;
    TextView dummy2;
    EditText dumyText;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);

        ie = new InplaceEditor(this);
        ie.setOnTextupdateListener(new TextUpdateListener() {
            @Override
            public void textUpdate() {
                System.out.println("Text has changed!");
            }
        });
        ie2 = new InplaceEditor(this,false,getResources().getDrawable(R.drawable.sape));
        dummy2=new TextView(this);


        dummy=new TextView(this);
        dummy.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In dapibus, lectus non consequat pretium, nibh lectus gravida lacus, condimentum laoreet risus turpis euismod nunc.");

        dummy2.setText("Vivamus maximus, est nec accumsan consectetur, elit dui tincidunt ipsum, eu imperdiet sapien ex eget neque.");
        dumyText=new EditText(this);
        ok = new Button(this);
        ok.setText("Ok");
        ie.setText("Header one");
        ie2.setText("Header two");
        l.addView(ie);
        l.addView(dummy);
        l.addView(ie2);
        l.addView(dummy2);
        l.setOnClickListener(lClick);
        l.addView(dumyText);
        l.addView(ok);
        l.setPadding(20, 20, 20, 20);



        setContentView(l);
    }


    View.OnClickListener lClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           ie.clearFocus();
            ie2.clearFocus();
        }
    };

}
