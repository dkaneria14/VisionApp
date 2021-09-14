package com.example.insertimage;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    Button buttonConvertText;
    TextView textView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickMe = findViewById(R.id.clickMe);
        buttonConvertText = findViewById(R.id.buttonConvertText);
        textView = findViewById(R.id.textView1);
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"Pick an image"),1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode ==1){
             imageView = findViewById(R.id.imageView2);
            imageView.setImageURI(data.getData());
            buttonConvertText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConvertImageToText( v,data);
                }
            });






        }
    }

    public void ConvertImageToText(View view,Intent data){
        // Storing the image from the imageView to the bitmap
        // Casted imageView to Bitmap drawableand from there got its bitmap and stored it in the bitmap
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        //Using the method from vision API to recognize the text
        // getApplicationContext because it will return the context of entire application and not the context of specific activity
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();


        //checking if TextRecognizer really detected any text or not!
        if(!textRecognizer.isOperational()){
            Toast.makeText(this, "Couldnt detect text! Try again", Toast.LENGTH_SHORT).show();
        }

        else{
            // setting the bitmap image to the frame class(from vision)||image info is stored in a Frame and this frame is detected by TextRecognizer
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            //The detect method(pre defined from vision api, takes a frame object)
            SparseArray<TextBlock> arrayOfText = textRecognizer.detect(frame);
            StringBuilder sb = new StringBuilder();
            for(int i =0;i<arrayOfText.size();i++){
                TextBlock tb = arrayOfText.get(i);
                sb.append(tb.getValue());
                sb.append("\n");
            }

            textView.setText(sb);
        }
    }







}