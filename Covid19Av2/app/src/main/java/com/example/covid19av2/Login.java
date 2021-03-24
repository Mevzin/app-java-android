package com.example.covid19av2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19av2.usuario.CadastroUsuario;
import com.example.covid19av2.usuario.Conexao;
import com.example.covid19av2.usuario.Usuario;
import com.example.covid19av2.usuario.UsuarioDAO;


public class Login extends AppCompatActivity {


    private EditText txtLogin;
    private EditText txtSenha;
    private Button btSalvar;
    private Button btCadastro;

    Conexao db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Conexao(this);


        txtLogin = findViewById(R.id.txtAutLogin);
        txtSenha = findViewById(R.id.txtAutSenha);
        btSalvar = findViewById(R.id.btSalvar);
        btCadastro = findViewById(R.id.btCadastro);




        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtLogin.getText().toString();
                String password = txtSenha.getText().toString();

                if (username.equals("")){
                    Toast.makeText(Login.this,"Nome de usuario não informado!", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(Login.this,"Senha não informada!", Toast.LENGTH_SHORT).show();
                }else{
                   String res =  db.ValidarLogin(username,password);

                   if (res.equals("OK")){
                       Toast.makeText(Login.this,"Logado com sucesso!", Toast.LENGTH_SHORT).show();
                       Intent logar = new Intent(Login.this, MenuP.class);
                       startActivity(logar);
                   }else{
                       Toast.makeText(Login.this,"Dados incorretos!", Toast.LENGTH_SHORT).show();
                   }

                }

            }
        });

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroTela = new Intent(Login.this, CadastroUsuario.class);
                startActivity(cadastroTela);
            }
        });


    }



}



