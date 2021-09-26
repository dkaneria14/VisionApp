package com.example.insertimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    String url;
    TextView dummyTextView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnDefinition = findViewById(R.id.btnDefinition);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         dummyTextView = findViewById(R.id.dummyTextView);
         editText   = findViewById(R.id.editTextTextMultiLine2);
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        editText.setText(text);




    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        String wordNeeded = editText.getText().toString();
        final String word = wordNeeded;
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }


    public void sendRequestOnClick(View v){

        DictionaryRequest dr = new DictionaryRequest(this,dummyTextView);
        url = dictionaryEntries();
        dr.execute(url);

    }

    public void settingTextView(String theRealDefinition){
      System.out.println(theRealDefinition);


    }
}