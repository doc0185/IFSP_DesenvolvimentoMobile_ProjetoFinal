package br.edu.ifsp.dmo.projetodmo.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.edu.ifsp.dmo.projetodmo.Util.Constant;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = Constant.DATABASE_FILE_NAME;
    public static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = UsuarioDAOSQLite.createTable();
        sqLiteDatabase.execSQL(sql);
    }

    // SEPARADO PARA FAZER DEPOIS
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
