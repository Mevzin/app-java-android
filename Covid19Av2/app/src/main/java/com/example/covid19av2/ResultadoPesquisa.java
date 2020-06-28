package com.example.covid19av2;

import androidx.annotation.NonNull;

public class ResultadoPesquisa {

        private String city;
        private String confirmed;
        private String date;
        private String death;
        private String state;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cidade: " + getCity()
                +"\n Estado: " + getState()
                +"\n Confirmados: " + getConfirmed()
                +"\n Data: " + getDate()
                +"\n Mortes: " + getDeath();
    }
}
