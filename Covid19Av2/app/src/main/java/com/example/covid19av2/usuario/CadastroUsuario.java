package com.example.covid19av2.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19av2.Login;
import com.example.covid19av2.R;

public class CadastroUsuario extends AppCompatActivity {

        private EditText nome;
        private EditText email;
        private EditText login;
        private EditText senha;
        private Button voltar;
        private Button loga;
        private UsuarioDAO dao;
        private Usuario usuario = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        loga = findViewById(R.id.btVoltarLogin);
        voltar = findViewById(R.id.btVoltarCadastro);
        nome = findViewById(R.id.edNome);
        email = findViewById(R.id.edEmail);
        login = findViewById(R.id.edLogin);
        senha = findViewById(R.id.edSenha);
        dao = new UsuarioDAO(this);

        voltar.setVisibility(View.INVISIBLE);
        loga.setVisibility(View.VISIBLE);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent it = getIntent();
        if (it.hasExtra("usuario")){
            usuario = (Usuario) it.getSerializableExtra("usuario");
            nome.setText(usuario.getNome());
            email.setText(usuario.getEmail());
            senha.setText(usuario.getSenha());
            login.setText(usuario.getLogin());

            voltar.setVisibility(View.VISIBLE);
            loga.setVisibility(View.INVISIBLE);

        }



    }

    public void salvar (View view){


                String name = nome.getText().toString();
                String emaiu = email.getText().toString();
                String username = login.getText().toString();
                String password = senha.getText().toString();



                if (name.equals("")){
                    Toast.makeText(CadastroUsuario.this,"Nome não informado!", Toast.LENGTH_SHORT).show();
                }else if(emaiu.equals("")){
                    Toast.makeText(CadastroUsuario.this,"Email não informado!", Toast.LENGTH_SHORT).show();
                }else if(username.equals("")){
                    Toast.makeText(CadastroUsuario.this,"Username não informado!", Toast.LENGTH_SHORT).show();
                }else if(password.equals("")){
                    Toast.makeText(CadastroUsuario.this,"Senha não informada!", Toast.LENGTH_SHORT).show();
                }else{
                    if (usuario == null) {
                    //tudo ok
                    usuario = new Usuario();
                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setLogin(login.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    dao.inserir(usuario);
                    Toast.makeText(this, "Usuario cadastrado !", Toast.LENGTH_SHORT).show();

                    nome.setText("");
                    email.setText("");
                    login.setText("");
                    senha.setText("");

                }else{
                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setLogin(login.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    dao.atualizar(usuario);
                    Toast.makeText(this, "Usuario atualizado !", Toast.LENGTH_SHORT).show();

                }


            }
    }
}
