package com.example.evaluacion_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ventana_resultados extends AppCompatActivity {
    private RequestQueue mQueue;
    private String token = "6563503487025578";
    private String heroe = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_resultados);
        mQueue = Volley.newRequestQueue(this);

        Intent main = getIntent();
        this.heroe = (String) main.getExtras().get("heroe");
        buscarHeroe(this.heroe);

    }

    public void buscarHeroe(String heroe) {
        TextView num_resultados = findViewById(R.id.txt_numResultados);
        num_resultados.setText(heroe);

        /*String url_temp = "https://superheroapi.com/api.php/"+token+"/search/"+str_heroe;
        JsonObjectRequest request = new JsonObjectRequest(
        Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            TextView resultado = (TextView) findViewById(R.id.txt_heroeResultado);
                            resultado.setText(response.getString(str_heroe));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        mQueue.add(request);*/
    }

}