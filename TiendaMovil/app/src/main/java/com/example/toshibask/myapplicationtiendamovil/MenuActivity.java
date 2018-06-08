package com.example.toshibask.myapplicationtiendamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void openSacnner(View view)
    {
        Intent intents = new Intent(getApplicationContext(), ScannerActivity.class);
        startActivity(intents);

    }
}
