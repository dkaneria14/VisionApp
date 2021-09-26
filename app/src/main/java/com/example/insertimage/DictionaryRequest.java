package com.example.insertimage;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class DictionaryRequest extends AsyncTask<String, Integer, String> {

    Context c;
    TextView tv;
    public DictionaryRequest(Context c, TextView textView){
        this.c =c;
        this.tv = textView;
    }



    @Override
    protected String doInBackground(String... params) {

        //TODO: replace with your own app id and app key
        final String app_id = "dd50fedf";
        final String app_key = "a2c8825a6dfe259e123177b0f932f518";
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject js = new JSONObject(result);
            JSONArray results = js.getJSONArray("results");
            JSONObject jobj = results.getJSONObject(0);
            JSONArray lexicalEntries = jobj.getJSONArray("lexicalEntries");
            JSONObject objOfLexicalEntries = lexicalEntries.getJSONObject(0);
            JSONArray entries = objOfLexicalEntries.getJSONArray("entries");
            JSONObject objOfEntries = entries.getJSONObject(0);
            JSONArray senses = objOfEntries.getJSONArray("senses");
            JSONObject objOfSenses = senses.getJSONObject(0);
            JSONArray definitions = objOfSenses.getJSONArray("definitions");

            String theRealDefinition;
             theRealDefinition = definitions.getString(0);

                tv.setText(theRealDefinition);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        /*System.out.println(theRealDefinition);*/

        MainActivity2 m2 = new MainActivity2();
      //  m2.settingTextView(theRealDefinition);
    }
}



