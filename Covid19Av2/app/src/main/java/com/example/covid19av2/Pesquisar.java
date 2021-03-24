package com.example.covid19av2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pesquisar extends AppCompatActivity {
    Button pesq;
    TextView dataSitu;
    Spinner spinner;
    Spinner spinnerCity;
    String estado;
    String urlEstado = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private String ibge;
    Switch dataS;
    ArrayList<String> StateName;
    ArrayList<String> Cityid;
    ArrayList<String> tes;
    String dataLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        pesq = findViewById(R.id.btPesquisar);
        dataSitu = findViewById(R.id.txData);
        tes = new ArrayList<>();
        Cityid = new ArrayList<>();
        StateName = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.country_Name);
        spinnerCity = (Spinner) findViewById(R.id.city_nome);
        loadSpinnerData(urlEstado);
        dataLast="";



        dataS = (Switch) findViewById(R.id.swData);
        dataSitu.setText("Mostrar todos os registros!");

        Button voltarbt = findViewById(R.id.btVoltar6);

        voltarbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        pesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent pq = new Intent(Pesquisar.this, ResultadoPesquisa.class);
                Bundle pqDados = new Bundle();

                pqDados.putString("chave_sigla", estado);
                pqDados.putString("chave_idIbge", ibge);
                pqDados.putString("chave_data", dataLast);

                pq.putExtras(pqDados);

                startActivity(pq);

            }
        });
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                estado = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                buscaCidade(estado);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              int cidade23 = spinnerCity.getSelectedItemPosition();
              int p = cidade23;
              String idIbge = Cityid.get(p);
              ibge = idIbge;


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }


        private void loadSpinnerData (String url) {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {


                           JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String estado = jsonObject1.getString("sigla");
                                StateName.add(estado);
                            }
                        spinner.setAdapter(new ArrayAdapter<String>(Pesquisar.this, android.R.layout.simple_spinner_dropdown_item, StateName));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            int socketTimeout = 30000;

            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            stringRequest.setRetryPolicy(policy);

            requestQueue.add(stringRequest);

        }

        private void buscaCidade(String sigla){

            tes.clear();
            Cityid.clear();
            spinnerCity.setAdapter(new ArrayAdapter<String>(Pesquisar.this, android.R.layout.simple_spinner_dropdown_item, tes));
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+sigla+"/municipios", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray jsonArray = new JSONArray(response);
                          for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String cidade = jsonObject1.getString("nome");
                            String idIbge = jsonObject1.getString("id");

                            Cityid.add(idIbge);
                            tes.add(cidade);
                        }

                        spinnerCity.setAdapter(new ArrayAdapter<String>(Pesquisar.this, android.R.layout.simple_spinner_dropdown_item, tes));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            int socketTimeout = 30000;

            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            stringRequest.setRetryPolicy(policy);

            requestQueue.add(stringRequest);


        }


    public void onclick(View view) {
        if(view.getId()==R.id.swData){
            if(dataS.isChecked()){
                dataLast="true";
                dataSitu.setText("Mostrar somente o ultimo registro!");

            }else{
                dataLast="";
                dataSitu.setText("Mostrar todos os registros!");

            }
        }

    }
}

