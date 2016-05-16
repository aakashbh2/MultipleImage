package com.example.aakash.imagemultiple;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskMarvel extends AsyncTask<String,String,List<MarvelModel>> {

    BufferedReader reader;
    private Context context;
    private GridView lv;

    public JsonTaskMarvel(Context context, GridView gridview) {
        this.context=context;
        lv=gridview;
    }


    @Override
    protected List<MarvelModel> doInBackground(String... params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(params[0]);

            try {
                connection= (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer =new StringBuffer();
                String line="";
                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                int code = connection.getResponseCode();
                Log.d("res", Integer.toString(code));

                String finalJson=buffer.toString();

                try {
                    JSONObject parent_object=new JSONObject(finalJson);
                     JSONArray results_array = parent_object.getJSONArray("result");

                    List<MarvelModel> marvelmodellist =new ArrayList<>();

                    for (int i=0;i<results_array.length();i++) {
                        JSONObject final_object = results_array.getJSONObject(i);
                        MarvelModel marvelmodel = new MarvelModel();

                        marvelmodel.setUrl(final_object.getString("url"));

                        marvelmodellist.add(marvelmodel);
                    }
                    return marvelmodellist;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection!=null) {
                connection.disconnect();
            }
            try {
                if (reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    protected void onPostExecute(List<MarvelModel> result) {
        super.onPostExecute(result);
         MarvelAdapter adapter= new MarvelAdapter(context,R.layout.row,result);
        lv.setAdapter(adapter);

    }
}