package com.example.izmir.lab3;

/**
 * Created by Izmir on 2015-11-24.
 */


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;

import java.util.List;

public class itemAdapter extends BaseAdapter {
    Context theContext;
    List<String> theNames;

    public itemAdapter(Context context, List<String> theList){
        theContext = context;
        theNames = theList;
    }

    @Override
    public int getCount() {
        return theNames.size();
    }

    @Override
    public Object getItem(int i) {
        return theNames.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        theItem result = new theItem(theContext, theNames.get(i));

        return result;
    }
}
