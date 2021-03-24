package com.example.covid19av2;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HTTPService extends AsyncTask<Void , Void , ResultadoPesquisa> {

    private final String Pesquisa;

    public HTTPService(String pesquisa) {
        this.Pesquisa = pesquisa;
    }

    @Override
    protected ResultadoPesquisa doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
           URL url = new URL("https://brasil.io/api/dataset/covid19/caso/data"+this.Pesquisa);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "applicantion/json");
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());

        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
