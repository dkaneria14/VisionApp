package com.example.insertimage;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button clickMe = findViewById(R.id.clickMe);
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
            ImageView imageView = findViewById(R.id.imageView2);


           // try{
               // InputStream inputStream = getContentResolver().openInputStream(data.getData());

              // Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
             // imageView.setImageBitmap(bitmap);
            imageView.setImageURI(data.getData());

         //   } catch (FileNotFoundException e) {
          //      e.printStackTrace();
          //  }

        }
    }







}