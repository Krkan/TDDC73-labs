

package com.example.izmir.lab3;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izmir on 2015-11-15.
 */
public class InteractiveSearcher extends EditText {

    private class SearchResult {

        private int theID;
        private List<String> resultString;

        public SearchResult(int i, List<String> ls) {
            theID = i;
            resultString = ls;
        }


        public int getID() {
            return theID;
        }

        public List<String> getList() {
            return resultString;
        }

    }

    ;


    private class SearchData {

        private int theID;
        private String searchString;

        public SearchData(int i, String ls) {
            theID = i;
            searchString = ls;
        }


        public int getID() {
            return theID;
        }

        public String getSearchString() {
            return searchString;
        }

    }

    ;

    private class networkData extends AsyncTask<SearchData, Void, String> {


        @Override
        protected String doInBackground(SearchData... searchData) {

            int id = searchData[0].getID();
            String search = searchData[0].getSearchString();

            HttpURLConnection connection = null;
            BufferedReader bfreader = null;

            try {
                URL url = new URL("http://flask-afteach.rhcloud.com/getnames/" + id + "/" + search);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream istream = connection.getInputStream();
                bfreader = new BufferedReader(new InputStreamReader(istream));
                StringBuffer sb = new StringBuffer();

                String line = "";

                while ((line = bfreader.readLine()) != null) {
                    sb.append(line);
                }


                return sb.toString();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {

                    if (bfreader != null) {
                        bfreader.close();
                    }
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            // super.onPostExecute(s);
            SearchResult theResult = InteractiveSearcher.this.getJsonResults(s);


            if (InteractiveSearcher.this.ID == theResult.getID()) {
                InteractiveSearcher.this.listItems = theResult.getList();
                InteractiveSearcher.this.myAdapter = new ArrayAdapter<String>(theContext, R.layout.support_simple_spinner_dropdown_item, listItems);
                InteractiveSearcher.this.theListPopup.setAdapter(myAdapter);
                InteractiveSearcher.this.theListPopup.show();


            }


        }
    }

    ;

    private ListPopupWindow theListPopup;
    private Context theContext;
    private List<String> listItems;
    private ArrayAdapter<String> myAdapter;
    private int ID = 0;
    private String searchText = "";
    private int nrResults=4;
    private int minSearchLength =3;

    public InteractiveSearcher(Context context) {
        super(context);
        theContext = context;
        this.init();
    }

    private void init() {
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        theListPopup = new ListPopupWindow(theContext);
        //Move to main activity later
        theListPopup.setListSelector(getResources().getDrawable(R.drawable.list_item_selector));
        listItems = new ArrayList<>();
        myAdapter = new ArrayAdapter<String>(theContext, R.layout.support_simple_spinner_dropdown_item, listItems);
        theListPopup.setAdapter(myAdapter);
        theListPopup.setAnchorView(this);
        this.addTextChangedListener(onTextChange);

    }


    private SearchResult getJsonResults(String data) {
        List<String> results = new ArrayList<>();
        int theID = -1;

        try {
            JSONObject JSON = new JSONObject(data);
            JSONArray jsonArray = JSON.getJSONArray("result");
            theID = JSON.getInt("id");
            for (int i = 0; i < jsonArray.length(); i++) {
                results.add(jsonArray.get(i).toString());
                if(i==nrResults){break;}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SearchResult(theID, results);
    }


    TextWatcher onTextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            theListPopup.dismiss();
            if (s.length() >= minSearchLength) {
                searchText = s.toString();
                ID++;
                networkData newThread = new networkData();
                newThread.execute(new SearchData(ID, searchText));
            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
//set nr of search results
    public void setNrResults(int nr){

        nrResults = nr-1;

    }
//set min searchtext length
    public void setMinSearchLength(int nr){

        minSearchLength=nr;
    }

}


