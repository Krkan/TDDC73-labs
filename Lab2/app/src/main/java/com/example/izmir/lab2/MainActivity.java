package com.example.izmir.lab2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;


public class MainActivity extends Activity {

    MovieAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<String> horror = new ArrayList<String>();
    List<String> action = new ArrayList<String>();
    List<String> scifi = new ArrayList<>();
    EditText navText;
    int lastExpandedPosition = -1;
    int lastMarkedGroup = -1;
    int lastMarkedChild = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navText = (EditText) findViewById(R.id.editText);
        navText.setText("€/€");
        navText.setBackgroundColor(Color.parseColor("#f4f4f4"));

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new MovieAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setFocusableInTouchMode(true);
        expListView.setItemsCanFocus(true);

        //Handles what happens when you click on a child object./*
        expListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                String groupName = getGroupName(groupPosition);
                String childName = getChildName(groupPosition, childPosition);
                navText.setText("/" + groupName + "/" + childName);

                return true;
            }
        });



        //Handle the group click navigation
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (expListView.isGroupExpanded(groupPosition)) {
                    expListView.collapseGroup(groupPosition);
                    navText.setText("€/€");
                } else {
                    String groupName = getGroupName(groupPosition);

                    navText.setText("/" + groupName);
                }

                return true;
            }
        });


        //Handle the navigation by EditText
        navText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (!s.toString().equals("") && s.charAt(0) == '/') {

                        String groupName = getGroupString(s.toString());
                        int groupPosition = _groupPosition(groupName);
                        String childName = getChildString(s.toString());
                        int childPosition = _getChildPosition(groupName, childName);

                        if (groupPosition != -1) {

                            expListView.expandGroup(groupPosition);
                            navText.setBackgroundColor(Color.parseColor("#f4f4f4"));

                        } else {
                            expListView.collapseGroup(lastExpandedPosition);
                            if (!groupName.equals("")) {
                                navText.setBackgroundColor(Color.RED);
                            }
                        }

                        int pos = _getPosition(groupName, childName);
                        if (childPosition != -1) {


                           int index =expListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));

                            expListView.setItemChecked(index, true);
                            navText.setBackgroundColor(Color.parseColor("#f4f4f4"));
                            lastMarkedChild=childPosition;
                            lastMarkedGroup=groupPosition;

                        } else {

                            if(lastMarkedChild!=-1 && lastMarkedGroup!=-1) {
                                try{
                                int index = expListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(lastMarkedGroup, lastMarkedChild));
                                expListView.setItemChecked(index, false);}catch(Exception e){}
                                System.out.println("Entering");
                            }
                            if (!childName.equals("")) {
                                navText.setBackgroundColor(Color.RED);
                            }

                        }


                    } else if (s.toString().equals("")) {
                        navText.setBackgroundColor(Color.parseColor("#f4f4f4"));
                    }
                }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //save the last expanded position
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                expListView.clearChoices();


                lastExpandedPosition = groupPosition;


            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                expListView.clearChoices();
            }
        });


    }

    //Gets child position, returns -1 if nothing is found
    private int _getChildPosition(String groupName, String childName) {

        int index = -1;
        if (!childName.equals("")) {

            List<String> listOfChild = listDataChild.get(groupName);


            for (int i = 0; i < listOfChild.size(); i++) {

                if (listOfChild.get(i).startsWith(childName)) {
                    index = i;
                    break;

                }


            }
        }
        return index;
    }

    private int _getPosition(String groupName, String childName) {
        int index = -1;
        if (!childName.equals("")) {
            for (String key : listDataChild.keySet()) {
                index++;
                for (String child : listDataChild.get(key)) {
                    index++;
                    if (key.equals(groupName) && child.equals(childName))
                        return index;
                }
            }
        }
        return -1;
    }


    //Gets group position from string, returns -1 if nothing is found.
    private int _groupPosition(String groupString) {

        int index = -1;

        for (int i = 0; i < listDataHeader.size(); i++) {

            if (listDataHeader.get(i).startsWith(groupString) && !groupString.equals("")) {
                index = i;
                break;
            }


        }

        return index;

    }

    //Converts the first part of the string aquired from the edit text to its proper form
    private String getGroupString(String navigation) {

        if (navigation.charAt(0) == '/') {

            navigation = navigation.substring(1, navigation.length());


            if (navigation.contains("/")) {


                int index = navigation.indexOf('/');

                navigation = navigation.substring(0, index);


            }

        }


        return navigation;

    }

    //Converts the second part of the string aquired from the edit text to its proper form

    private String getChildString(String navigation) {


        if (navigation.charAt(0) == '/') {

            navigation = navigation.substring(1, navigation.length());


            if (navigation.contains("/")) {


                int index = navigation.indexOf('/');
                navigation = navigation.substring(index + 1, navigation.length());


            } else {
                navigation = "";
            }

        }


        return navigation;
    }

    //Get the group name from the position
    private String getGroupName(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    //Get the child name from a position
    private String getChildName(int groupPosition, int childPosition) {

        String groupName = listDataHeader.get(groupPosition);
        List<String> theChildren = listDataChild.get(groupName);

        return theChildren.get(childPosition);

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("horror");
        listDataHeader.add("action");
        listDataHeader.add("scifi");

        // Adding child data
        horror.add("halloween");
        horror.add("the thing");
        horror.add("the shining");
        horror.add("alien");

        action.add("terminator");
        action.add("die hard");
        action.add("predator");

        scifi.add("star gate");
        scifi.add("star gate");
        scifi.add("star trek");
        scifi.add("battlestar galactica");
        scifi.add("interstellar");


        listDataChild.put(listDataHeader.get(0), horror); // Header, Child data
        listDataChild.put(listDataHeader.get(1), action);
        listDataChild.put(listDataHeader.get(2), scifi);
    }
}