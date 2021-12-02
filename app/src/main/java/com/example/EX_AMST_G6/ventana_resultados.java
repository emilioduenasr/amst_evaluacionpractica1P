package com.example.EX_AMST_G6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ventana_resultados extends AppCompatActivity {
    private RequestQueue mQueue;
    private final String token = "6563503487025578";
    private String heroe = "";
    private ArrayList<String> lista_nombres = new ArrayList<String>();
    private ArrayList<String> lista_id = new ArrayList<String>();

    LinearLayout resultados_layout;

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
        TextView txt_resultado = findViewById(R.id.resultado_json);
        resultados_layout = findViewById(R.id.resultados_layout);

        String url_temp = "https://superheroapi.com/api.php/"+token+"/search/"+heroe;

        JsonObjectRequest request = new JsonObjectRequest(
        Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("JSON response: \n"+response+"\n");
                        try {
                            //Lista de heroes (JSON)
                            JSONArray resultados_json = response.getJSONArray("results");
                            Integer total_resultados = resultados_json.length();
                            System.out.println("Total resultados: "+total_resultados);
                            num_resultados.setText(total_resultados.toString());

                            for (int i=0; i<resultados_json.length(); i++) {
                                JSONObject heroe = resultados_json.getJSONObject(i);
                                TextView txt_heroe_result = new TextView(getBaseContext());
                                txt_heroe_result.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                                txt_heroe_result.setText(heroe.getString("name"));
                                txt_heroe_result.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            Intent intent = new Intent(getBaseContext(), ventana_detalles.class);
                                            intent.putExtra(ventana_detalles.ID, heroe.getInt("id"));
                                            intent.putExtra("token", token);
                                            startActivity(intent);
                                        } catch (Exception ignored) {}
                                    }
                                });
                                resultados_layout.addView(txt_heroe_result);
                            }

                            System.out.println("Heroe JSON: \n"+resultados_json+"\n");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("VolleyError", error.toString());
                    }
                });
                mQueue.add(request);
    }
}