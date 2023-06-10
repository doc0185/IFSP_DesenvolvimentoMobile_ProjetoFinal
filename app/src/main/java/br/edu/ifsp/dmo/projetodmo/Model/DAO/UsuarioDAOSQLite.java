package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    @Override
    public boolean loginUser(String username, String senha) {
        Usuario usuario = null;
        String columns[] = new String[]{
                Constant.ATTR_FULLNAME,
                Constant.ATTR_USERNAME,
                Constant.ATTR_CITY,
                Constant.ATTR_STATE,
                Constant.ATTR_PHONE,
                Constant.ATTR_PASSWORD
        };

        String selection = Constant.ATTR_USERNAME + " = ? ";
        selection += "AND " + Constant.ATTR_PASSWORD + " = ? ";
        String selectionArgs[] = {username, senha};

        try{
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    Constant.ENTITY_USUARIO,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            if (cursor.moveToNext()){
                usuario = new Usuario(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5));
            }
            cursor.close();
        } catch (Exception e){
            usuario = null;
            return false;
        } finally{
            mDatabase.close();
        }
        return true;
    }
}
