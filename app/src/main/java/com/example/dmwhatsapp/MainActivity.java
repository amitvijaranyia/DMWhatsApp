package com.example.dmwhatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMobNumber;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMobNumber = findViewById(R.id.etMobNumber);
        imageButton = findViewById(R.id.imageButton);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if(Intent.ACTION_PROCESS_TEXT.equals(action) && type != null){
            if ("text/plain".equals(type)) {
                String number = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT);
                    whatsAPP(number);
           }
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobNumber = etMobNumber.getText().toString();
                whatsAPP(mobNumber);
            }
        });
    }

    void whatsAPP(String mobNumber){
        try{
            if(mobNumber != null && mobNumber.charAt(0) == '0'){
                mobNumber = mobNumber.substring(1);
            }
            if(mobNumber != null && mobNumber.charAt(0) == '+'){
                mobNumber = mobNumber.substring(1);
            }
            if(mobNumber != null && mobNumber.length() == 10) {
                mobNumber = "+91"+mobNumber;
            }
            Uri uri = Uri.parse("https://wa.me/" + mobNumber);
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(i);
            finish();
        }
        catch (Exception e){
            Toast.makeText(MainActivity.this,
                    "SOMETHING WRONG HAPPENED",
                    Toast.LENGTH_SHORT)
                    .show();
        }

    }
}
