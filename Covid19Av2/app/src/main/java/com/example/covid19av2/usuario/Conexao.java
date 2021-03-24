package com.example.covid19av2.usuario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "crud3.db";
    private static final int version = 1;

    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table usuario( id integer primary key autoincrement,"+
                    "nome carchar(100), email varchar(100), login varchar(100) ,senha varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String ValidarLogin (String login, String senha){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usuario WHERE login=? AND senha=?",new String[]{login,senha});
        if(c.getCount()>0){
            return"OK";
        }
        return"ERRO";
    }
}
