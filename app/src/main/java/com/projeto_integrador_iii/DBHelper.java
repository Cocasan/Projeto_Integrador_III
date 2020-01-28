package com.projeto_integrador_iii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String NOME= "bd_prato.db";

    private static int VERSAO =1;

    public DBHelper(Context context){
        super(context, NOME,null, VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE tbl_prato(" +
                "codigo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomeprato VARCHAR(60)NOT NULL," +
                "descricao VARCHAR(60) NOT NULL," +
                "quantidade INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
