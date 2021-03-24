package com.example.covid19av2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.covid19av2.usuario.ListaUsuario;

public class MenuP extends AppCompatActivity {

    private Button busca;
    private Button sobre;
    private Button cadastro;
    private Button fechar;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_p);

        busca = findViewById(R.id.btBusca);
        cadastro = findViewById(R.id.btLista);
        sobre = findViewById(R.id.btSobre);
        fechar = findViewById(R.id.btClose);
        logout = findViewById(R.id.btLogout);

        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bC = new Intent(MenuP.this , Pesquisar.class);
                startActivity(bC);
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bCT = new Intent(MenuP.this , ListaUsuario.class);
                startActivity(bCT);
            }
        });

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bS = new Intent(MenuP.this , Sobre.class);
                startActivity(bS);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = new AlertDialog.Builder(MenuP.this).setTitle("Atenção").setMessage("Realmente deseja fechar o aplicativo ?").setNegativeButton("Não",null)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        }).create();
                dialog.show();
            }
        });


    }
}
