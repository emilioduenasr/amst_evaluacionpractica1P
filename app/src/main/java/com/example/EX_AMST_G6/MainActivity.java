package com.example.EX_AMST_G6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irVentanaResultados(View v) {
        EditText heroe = (EditText) findViewById(R.id.txt_busqueda);
        String str_heroe = heroe.getText().toString();

        Intent resultados = new Intent(getBaseContext(),ventana_resultados.class);
        resultados.putExtra("heroe", str_heroe);
        startActivity(resultados);
    }
}
