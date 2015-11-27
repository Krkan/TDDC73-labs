package com.example.izmir.passwordstrengthmeter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Izmir on 2015-11-18.
 *
 * Class that handles the GUI elements of the PasswordStrength meter. It uses the class PassAlg
 * to acquire the password strength.
 * It also contains some "shortcut" methods to some methods in PassAlg that makes the class more intuitive
 * to use.
 */

public class PasswordStrengthMeter extends LinearLayout {
    private Context theContext;
    private EditText input;
    private TextView strengthText;
    private TextView str;
    private LinearLayout seperator;
    private ProgressBar progressBar;
    private PassAlg alg;
    private boolean hasBar;
    private boolean hasStrTxt;



    //Default constructor
    public PasswordStrengthMeter(Context context){
        super(context);
        theContext = context;
        alg = new PassAlg();
        hasBar=true;
        hasStrTxt=true;
        init();
    }

//Constructor where you can specify minimum password length, if the bar is shown and strength text is shown
    public PasswordStrengthMeter(Context context, int minimumLength, boolean setBar, boolean setStrText){
        super(context);
        theContext = context;
      alg= new PassAlg(minimumLength);
        hasBar=setBar;
        hasStrTxt=setStrText;
            init();
    }
//Constructor if you wish to provide the algorithm yourself
    public PasswordStrengthMeter(Context context, PassAlg algorithm, boolean setBar, boolean setStrText){
        super(context);
        theContext = context;
        alg= algorithm;
        hasBar=setBar;
        hasStrTxt=setStrText;
        init();
    }

    //Function that handles the GUI and sets event listeners, called from the constructor
    private void init(){
        this.setOrientation(LinearLayout.VERTICAL);
        input=new EditText(theContext);
        seperator=new LinearLayout(theContext);
        seperator.setOrientation(HORIZONTAL);
        input.setMaxLines(1);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        strengthText = new TextView(theContext);
        strengthText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        strengthText.setText("This password is");
        str = new TextView(theContext);
        str.setText(" too short");
        str.setTextColor(Color.GRAY);
        seperator.addView(strengthText);
        seperator.addView(str);
        progressBar = new ProgressBar(theContext,null,android.R.attr.progressBarStyleHorizontal);
        progressBar.setScaleY(3);
        progressBar.setMax(5);
        progressBar.setProgress(0);
        progressBar.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        this.addView(input);
        if(hasBar) {
            this.addView(progressBar);
        }
        if(hasStrTxt) {
            this.addView(seperator);
        }
        input.addTextChangedListener(inputWatcher);

    }

    //Public function that will return the text from the input field
    public String getText(){
        return input.getText().toString();
    }

    //Public function the user can use to check if minimum length requirement was met.
    public boolean meetsMinimumLength(){

        return alg.meetsMinimumLength(input.getText().toString());
    }



    //Public function to check if password has an uppercase character
    public boolean hasUpper(){
        return (alg.hasUpper(input.getText().toString()));
    }




    //Public function to check if password has an lowercase character
    public boolean hasLower(){
        return (alg.hasLower(input.getText().toString()));
    }




    //Public function to check if password contains a number
    public boolean hasNumber(){
        return (alg.hasNumber(input.getText().toString()));
    }


    //Public function to check if password contains a special character
    public boolean hasSpecial(){
        return (alg.containSpecial(input.getText().toString()));
    }

    //Function to set the minimum password length
    public void setMinPasswordLength(int Length){
        alg.setMinPasswordLength(Length);
    }

//Changes the password strength string
    private void changeTextstr(int currentPoints){


        switch(currentPoints){
            case 1:
                str.setText(" very weak");
                str.setTextColor(Color.RED);
            break;
            case 2:
                str.setText(" weak");
                str.setTextColor(Color.parseColor("#FF6600"));
                break;
            case 3:
                str.setText(" fair");
                str.setTextColor(Color.parseColor("#FFFF66"));
                break;
            case 4:
                str.setText(" strong");
                str.setTextColor(Color.GREEN);
                break;
            case 5:
                str.setText(" very strong");
                str.setTextColor(Color.parseColor("#2C6700"));
        }


    }

//Updates the progress bar
    private void updateProgressBar(int currentPoints){

        progressBar.setProgress(currentPoints);

        switch(currentPoints){
            case 1:
                progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

                break;
            case 2:
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#FF6600"), PorterDuff.Mode.SRC_IN);

                break;
            case 3:
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#FFFF66"), PorterDuff.Mode.SRC_IN);

                break;
            case 4:
                progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

                break;
            case 5:
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#2C6700"), PorterDuff.Mode.SRC_IN);

        }



    }

    //Keeps track of what's typed in the edit text
    TextWatcher inputWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        String password=s.toString();
            int currentPoints =0;
            if (!password.equals("")){
                currentPoints=alg.getPoints(password);

                if(hasStrTxt) {
                    changeTextstr(currentPoints);
                }
                if(hasBar) {
                    updateProgressBar(currentPoints);
                }
                }
            else{
                str.setText(" too short");
                str.setTextColor(Color.GRAY);
                progressBar.setProgress(0);
                progressBar.getProgressDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
