package com.example.izmir.inplaceeditor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * Created by Izmir on 2015-11-19.
 *
 * InplaceEditor that can be used to edit text on the fly!
 */
public class InplaceEditor extends LinearLayout {


    private Context theContext;

    private EditText input;
    private Button save;
    private Button cancel;
    private LinearLayout buttonSplit;
    private String currentText="";
    private boolean useButtons;
    private Drawable background=null;
    private TextUpdateListener listener;
//Default constructor
    public InplaceEditor(Context context){

    super(context);
    theContext=context;
        useButtons=true;
    init();
}

    public void setOnTextupdateListener(TextUpdateListener l){
        listener=l;
    }
    //Constructor that let's you choose if you want buttons or not

    public InplaceEditor(Context context, boolean haveButtons){
        super(context);
        theContext=context;
        useButtons=haveButtons;
        init();
    }
//Constructor that let's the used use their own custom shape.
    public InplaceEditor(Context context, boolean haveButtons, Drawable shape){
        super(context);
        theContext=context;
        useButtons=haveButtons;
        background=shape;
        init();
    }

//Function called by the constructor, handles the GUI part of the component and sets the listeners
    private void init(){
        input=new EditText(theContext);
        save = new Button(theContext);
        cancel = new Button(theContext);
        buttonSplit = new LinearLayout(theContext);
        save.setText("Save");
        cancel.setText("Cancel");
        input.setMaxLines(1);
        input.setFocusable(false);
        input.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        if(background==null) {
            input.setBackgroundColor(Color.TRANSPARENT);
        }else{
            input.setBackground(background);
        }
        //input.setOnClickListener(textClick);
        input.setOnFocusChangeListener(ofc);
        buttonSplit.setOrientation(HORIZONTAL);
        this.setOrientation(VERTICAL);
        buttonSplit.addView(save);
        buttonSplit.addView(cancel);
        this.addView(input);
        //this.setOnClickListener(lClick);
        save.setOnClickListener(saveBtnListener);
        cancel.setOnClickListener(cancelBtnListener);
        input.setOnKeyListener(enterKeyListener);
        input.setOnLongClickListener(textClickLong);

    }



    //Clicking the edit text makes it editable.
    private OnClickListener textClick = new OnClickListener() {
        @Override
        public void onClick(View v) {

                input.setFocusable(true);
                input.setFocusableInTouchMode(true);
                input.requestFocus();


            }
    };

    private OnLongClickListener textClickLong = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            input.setFocusable(true);
            input.setFocusableInTouchMode(true);
            input.requestFocus();
            return true;
        }
    };






//Changing the focus from the edit text changes its background color and hides/shows the buttons
    private OnFocusChangeListener ofc = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            if(hasFocus){
               input.setBackgroundColor(Color.parseColor("#FFFFE0"));
                if(useButtons) {
                    InplaceEditor.this.addView(buttonSplit);
                }

            }else{
                if(background==null) {
                    input.setBackgroundColor(Color.TRANSPARENT);
                }else{
                    input.setBackground(background);
                }
                input.setText(currentText);
               input.setFocusable(false);
                if(useButtons) {
                    InplaceEditor.this.removeView(buttonSplit);
                }


            }

        }
    };

//Clear the focus from on own view when pressing outside
   private  View.OnClickListener lClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           input.clearFocus();
        }
    };

    //Listener for the save button, saves the edited text
    private OnClickListener saveBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
        currentText=input.getText().toString();
            input.clearFocus();
            listener.textUpdate();
        }
    };
//Listener for the cancel button, returns the text to it's previous state
    private OnClickListener cancelBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            input.setText(currentText);
            input.clearFocus();
        }
    };

    //Key listener for the enter button, will save what's new inside the input text
    private OnKeyListener enterKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if(event.getAction() == KeyEvent.ACTION_DOWN){

                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    currentText=input.getText().toString();
                    input.clearFocus();
                    listener.textUpdate();
                }


            }


            return true;
        }
    };

//Function to clear the focus
    public void clearFocus(){
        input.clearFocus();
    }

    //Returns the written text
    public String getText(){
        return currentText;
    }

    //Function that can be used to set the text of the text field
    public void setText(String s){
        input.setText(s);
        currentText=s;
    }

    //Function that can be used to show/hide buttons on the fly
    public void showButtons(boolean showButtons){
        useButtons=showButtons;
    }

}
