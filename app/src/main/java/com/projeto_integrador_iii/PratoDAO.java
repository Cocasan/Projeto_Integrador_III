package com.projeto_integrador_iii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class PratoDAO {

    private Context context;

    private ArrayList<Prato> listapratos(){

        DBHelper dbHelper =null;
        SQLiteDatabase sqLiteDatabase=null;
        Cursor cursor= null;

        ArrayList<Prato> pratos=new ArrayList<>();

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("tbl_prato", null, null,
                    null, null, null, null);

            while (cursor.moveToNext()) {

                Prato prato = new Prato();

                prato.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                prato.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
                prato.setNomeprato(cursor.getString(cursor.getColumnIndex("nomeprato")));
                prato.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

                pratos.add(prato);   //add está em vermelho

            }

        }catch (Exception e) {
            e.printStackTrace();

        }  finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();

            if (sqLiteDatabase != null)
                sqLiteDatabase.close();

            if (dbHelper != null)
                dbHelper.close();
        }
        return pratos;
    }
    public boolean iserirPrato(Prato p) {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";

            if (p.getCodigo() == -1) {
                sql = "UPDATE tbl_prato SET nomeprato =?, descricao=?, quantidade=? where codigo=?";

            } else {
                sql = "INSERT INTO tbl_produto(nomeprato,descricao,quantidade) values(?,?,?)";
            }

            sqLiteDatabase.beginTransaction();

            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);

            sqLiteStatement.clearBindings();

            sqLiteStatement.bindString(1, p.getNomeprato());
            sqLiteStatement.bindString(2, p.getDescricao());
            sqLiteStatement.bindString(3, String.valueOf(p.getQuantidade()));

            if (p.getCodigo() == -1) {
                sqLiteStatement.bindString(4, String.valueOf(p.getCodigo()));
            }
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            sqLiteDatabase.endTransaction();

            return false;

        } finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();

            if (dbHelper != null)
                dbHelper.close();
        }

    }
    public boolean removerprato(int codigo){
        DBHelper dbHelper= null;                 // não aceita "dbHelper"
        SQLiteDatabase sqLiteDatabase = null;               // Não aceita "sqLiteDatabase"

        try{

            dbHelper = new DBHelper(context);
            sqLiteDatabase=dbHelper.getWritableDatabase();
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete("tbl_prato", "codigo", new String[]{String.valueOf(codigo)});
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();

            return true;
        } catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();

            return false;

        }   finally {

            if (sqLiteDatabase != null)
                sqLiteDatabase.close();

            if (dbHelper != null)
                dbHelper.close();
        }
    }
}
