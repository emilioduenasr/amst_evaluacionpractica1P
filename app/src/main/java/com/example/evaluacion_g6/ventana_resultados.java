package com.example.evaluacion_g6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ventana_resultados extends AppCompatActivity {
    private RequestQueue mQueue;
    private String token = "6563503487025578";
    private String heroe = "";
    private ArrayList<String> lista_nombres = new ArrayList<String>();
    private ArrayList<String> lista_id = new ArrayList<String>();

    ListView vistaHeroes;
    ArrayAdapter<String> adapter;

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

        String url_temp = "https://superheroapi.com/api.php/"+token+"/search/"+heroe;
        TextView url = findViewById(R.id.txt_URL);
        url.setText(url_temp);

        ArrayList<String> heroe_lista= new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(
        Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            //Lista de heroes (JSON)
                            JSONArray heroe_json = response.getJSONArray("results");
                            for (int i = 0, lista = heroe_json.length(); i < lista; i++) {
                                heroe_lista.add(heroe_json.getJSONObject(i).getString("name")+","+heroe_json.getJSONObject(i).getString("id"));
                            }

                            for (int i = 0, l = heroe_lista.size(); i < l; i++) {
                                String[] parts = heroe_lista.get(i).split(",");
                                lista_nombres.add(parts[0]);lista_id.add(parts[1]);

                            }

                            vistaHeroes = (ListView) findViewById(R.id.result_view);
                            //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista_nombres);
                            vistaHeroes.setAdapter(adapter);
                            //vistaHeroes.setOnItemClickListener(this);

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

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String url = "https://www.superheroapi.com/api.php/"+token+"/id/"+lista_nombres.get(i);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog alertDialog = new AlertDialog.Builder(ventana_resultados.this).create();
                    alertDialog.setTitle("Alerta");
                    alertDialog.setMessage("Credenciales Incorrectas");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int
                                        which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            });
            mQueue.add(request);

        }
}