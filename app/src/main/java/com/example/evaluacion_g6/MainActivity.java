package com.example.evaluacion_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RequestQueue mQueue = null;
    private String token = "6563503487025578";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
    }

    public void irVentanaResultados(View v) {
        EditText txt_heroe = (EditText) findViewById(R.id.txt_busqueda);
        String str_heroe = txt_heroe.getText().toString();
        buscarHeroe(str_heroe);

    }

    private void buscarHeroe(String heroe) {
        String url_temp = "https://superheroapi.com/api/"+token+"/search/"+heroe;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            TextView resultado = (TextView) findViewById(R.id.txt_Resultado);
                            resultado.setText(response.getString(heroe));
                            Intent ventana_resultados = new Intent(getBaseContext(), ventana_resultados.class);
                            ventana_resultados.putExtra("token",token);
                            startActivity(ventana_resultados);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        mQueue.add(request);
    }

}
