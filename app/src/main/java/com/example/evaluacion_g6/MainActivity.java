package com.example.evaluacion_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mQueue = Volley.newRequestQueue(this);
    }

    public void irVentanaResultados(View v) {
        EditText heroe = (EditText) findViewById(R.id.txt_busqueda);
        String str_heroe = heroe.getText().toString();

        Intent resultados = new Intent(getBaseContext(),ventana_resultados.class);
        resultados.putExtra("heroe", str_heroe);
        startActivity(resultados);


    }

}
