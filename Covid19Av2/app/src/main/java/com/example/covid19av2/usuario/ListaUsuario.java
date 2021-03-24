package com.example.covid19av2.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.covid19av2.Pesquisar;
import com.example.covid19av2.R;
import com.example.covid19av2.usuario.CadastroUsuario;
import com.example.covid19av2.usuario.Usuario;
import com.example.covid19av2.usuario.UsuarioDAO;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuario extends AppCompatActivity {



    private ListView listView;
    private UsuarioDAO dao;
    private List<Usuario> usuarios;
    private List<Usuario> usuariosFiltrados = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuario);

        listView = findViewById(R.id.ltUsuarios);
        dao = new UsuarioDAO(this);
        usuarios = dao.obterTodos();
        usuariosFiltrados.addAll(usuarios);

        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<Usuario>(this,android.R.layout.simple_list_item_1,usuariosFiltrados);
        listView.setAdapter(adaptador);

        registerForContextMenu(listView);
        Button btRetorno = findViewById(R.id.btVoltar6);

        btRetorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraUsurario(s);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto,menu);
    }


    public void procuraUsurario(String nome){
        usuariosFiltrados.clear();
        for (Usuario a : usuarios){
            if (a.getNome().toLowerCase().contains(nome.toLowerCase())){
                usuariosFiltrados.add(a);
            }
        }
    }

    public void cadastrar(MenuItem item){
        Intent it = new Intent(this, CadastroUsuario.class);
        startActivity(it);
    }

    @Override
    public void onResume(){
        super.onResume();
        usuarios = dao.obterTodos();
        usuariosFiltrados.clear();
        usuariosFiltrados.addAll(usuarios);
        listView.invalidateViews();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Usuario usuarioExcluir = usuariosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção").setMessage("Realmente deseja excluir este usuario ?").setNegativeButton("Não",null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuariosFiltrados.remove(usuarioExcluir);
                        usuarios.remove(usuarioExcluir);
                        dao.excluir(usuarioExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Usuario usuarioAtualizar = usuariosFiltrados.get(menuInfo.position);

        Intent it = new Intent(this, CadastroUsuario.class);
        it.putExtra("usuario", usuarioAtualizar);
        startActivity(it);

    }
    public void irPerquisarCovid(MenuItem item){
        Intent it = new Intent(this, Pesquisar.class);
        startActivity(it);
    }

}
