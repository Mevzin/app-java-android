package com.example.covid19av2.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

        private Conexao conexao;
        private SQLiteDatabase banco;

        public UsuarioDAO(Context context){
            conexao = new Conexao(context);
            banco = conexao.getWritableDatabase();
        }

        public long inserir (Usuario usuario){
            ContentValues values = new ContentValues();
            values.put("nome",usuario.getNome());
            values.put("email",usuario.getEmail());
            values.put("login",usuario.getLogin());
            values.put("senha",usuario.getSenha());
            return banco.insert("usuario",null, values);
        }
        public List<Usuario> obterTodos(){
            List<Usuario> usuarios = new ArrayList<>();
            Cursor cursor = banco.query("usuario", new String[]{"id","nome","email","login","senha"},null,null,null,null,null);
            while (cursor.moveToNext()){
                Usuario user = new Usuario();
                user.setId(cursor.getInt(0));
                user.setNome(cursor.getString(1));
                user.setEmail(cursor.getString(2));
                user.setLogin(cursor.getString(3));
                user.setSenha(cursor.getString(4));
                usuarios.add(user);

            }
            return usuarios;
        }
        public void excluir(Usuario user){
          banco.delete("usuario","id = ?", new String[]{user.getId()+""});
        }

        public void atualizar(Usuario user){
            ContentValues values = new ContentValues();
            values.put("nome",user.getNome());
            values.put("email",user.getEmail());
            values.put("login",user.getLogin());
            values.put("senha",user.getSenha());
            banco.update("usuario", values, "id = ?", new String[]{user.getId()+""});
        }

}
