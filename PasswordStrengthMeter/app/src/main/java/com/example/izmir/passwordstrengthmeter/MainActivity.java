package com.example.izmir.passwordstrengthmeter;

/*Simple test program to show the functionality of the password strength meter.
*
* */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PasswordStrengthMeter s;
    Toast t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s= new PasswordStrengthMeter(this, 7,true,true);
        t= new Toast(this);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(26,26,26,26);
        TextView u = new TextView(this);
        EditText uname = new EditText(this);
        TextView e = new TextView(this);
        EditText email = new EditText(this);
        TextView p = new TextView(this);

        Button ok = new Button(this);

        u.setText("Username");
        e.setText("E-mail");
        p.setText("Password");
        ok.setText("Submit");

        l.addView(u);
        l.addView(uname);
        l.addView(e);
        l.addView(email);
        l.addView(p);
        l.addView(s);
        l.addView(ok);


        setContentView(l);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(s.meetsMinimumLength()){
                    t.makeText(getApplicationContext(), "Password meets the required length!", Toast.LENGTH_SHORT).show();

                }else{
                    t.makeText(getApplicationContext(),"Password doesn't meet the required length!",Toast.LENGTH_SHORT ).show();

                }

            }
        });
    }
}
