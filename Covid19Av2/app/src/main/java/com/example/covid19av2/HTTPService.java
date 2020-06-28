package com.example.covid19av2;

import android.os.AsyncTask;

public class HTTPService extends AsyncTask<void , void , ResultadoPesquisa> {

    private final String Pesquisa;

    public HTTPService(String pesquisa) {
        Pesquisa = pesquisa;
    }

    @Override
    protected ResultadoPesquisa doInBackground(void... voids) {
        return null;
    }
}
