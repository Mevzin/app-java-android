package com.example.covid19av2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultadoPesquisa extends AppCompatActivity {
    private TextView mTextViewResilt;
    private RequestQueue mQueue;
    private Button voltar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_pesquisa);

        Intent receba = getIntent();
        Bundle paramentros = receba.getExtras();

        String sigla = paramentros.getString("chave_sigla");
        String ibge = paramentros.getString("chave_idIbge");
        String data = paramentros.getString("chave_data");

        mTextViewResilt = findViewById(R.id.text_view_result);
        voltar = findViewById(R.id.btVoltarPesquisa);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        mQueue = Volley.newRequestQueue(this);

        String url = "https://brasil.io/api/dataset/covid19/caso/data?state="+sigla+"&city_ibge_code="+ibge+"&is_last="+data;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject result = jsonArray.getJSONObject(i);

                        String estado = result.getString("state");
                        String cidade = result.getString("city");
                        int mortes = result.getInt("deaths");
                        int confirmados = result.getInt("confirmed");
                        String data = result.getString("date");

                        mTextViewResilt.append("Estado: "+estado+"\n"+
                                "     Cidade: "+cidade+
                                "\n        Confirmados: "+String.valueOf(confirmados)
                                +"\n        Mortes: "+String.valueOf(mortes)+
                                "\n        Data do resultado: "+data+"\n\n");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);


    }




}