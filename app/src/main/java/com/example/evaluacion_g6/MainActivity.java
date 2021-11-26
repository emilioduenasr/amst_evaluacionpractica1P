package com.example.evaluacion_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void irVentana2(View v){
        Intent ventana2 = new Intent(getBaseContext(), ventana2.class);
        startActivity(ventana2);
    }
}