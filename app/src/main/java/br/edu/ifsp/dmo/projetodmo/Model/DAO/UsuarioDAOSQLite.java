package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo.projetodmo.Model.Entities.Usuario;
import br.edu.ifsp.dmo.projetodmo.Util.Constant;

public class UsuarioDAOSQLite implements IUsuarioDAO{
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public UsuarioDAOSQLite (Context context){
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable(){
        String sql = "CREATE TABLE " + Constant.ENTITY_USUARIO + "(";
        sql += Constant.ATTR_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.ATTR_FULLNAME + " TEXT NOT NULL, ";
        sql += Constant.ATTR_USERNAME + " TEXT NOT NULL, ";
        sql += Constant.ATTR_CITY + " TEXT NOT NULL, ";
        sql += Constant.ATTR_STATE + " TEXT NOT NULL, ";
        sql += Constant.ATTR_PHONE + " TEXT NOT NULL, ";
        sql += Constant.ATTR_PASSWORD + " TEXT NOT NULL);";

        return sql;
    }

    @Override
    public void create(Usuario u) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constant.ATTR_FULLNAME, u.getNomeCompleto());
        contentValues.put(Constant.ATTR_USERNAME, u.getUsername());
        contentValues.put(Constant.ATTR_CITY, u.getCidade());
        contentValues.put(Constant.ATTR_STATE, u.getEstado());
        contentValues.put(Constant.ATTR_PHONE, u.getTelefone());
        contentValues.put(Constant.ATTR_PASSWORD, u.getSenha());
        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase.insert(Constant.ENTITY_USUARIO, null, contentValues);
        mDatabase.close();

    }
}
