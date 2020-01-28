package com.projeto_integrador_iii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Prato {
    private int codigo;
    private String nome;
    private int preco;
    private String ingrediente;
    private boolean excluir;
    private Context context;

    public Prato(Context context) {
        this.context = context;
        codigo = -1;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public boolean isExcluir() {
        return excluir;
    }

    public void setExcluir(boolean excluir) {
        this.excluir = excluir;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Prato> getPratos() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Prato> pratos = new ArrayList<>();

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("prato", null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                Prato prato = new Prato(context);
                prato.codigo = cursor.getInt(cursor.getColumnIndex("codigo"));
                prato.nome = cursor.getString(cursor.getColumnIndex("nome"));
                prato.ingrediente = cursor.getString(cursor.getColumnIndex("ingrediente"));
                prato.setPreco(cursor.getInt(cursor.getColumnIndex("codigo")));
                pratos.add(prato);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ((cursor != null) && (!cursor.isClosed()))
                cursor.close();
            if (sqLiteDatabase != null)
                sqLiteDatabase.close();
            if (dbHelper != null)
                dbHelper.close();
        }

        return pratos;

    }
    public boolean Salvar() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;


        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            String sql = "";

            if (codigo == -1) {
                sql = "INSERT INTO prato (nome,ingrediente,preco) VALUES (?,?,?)";
            } else {
                sql = "UPDATE prato set nome = ?,  ingrediente = ?, preco = ? WHERE codigo = ?";
            }

            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, nome);
            sqLiteStatement.bindString(2, ingrediente);
            sqLiteStatement.bindString(3, String.valueOf(preco));
            if (codigo != -1)
                sqLiteStatement.bindString(4, String.valueOf(codigo));
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
    public boolean Excluir() {
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;

        try {
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete("prato", "codigo = ?", new String[]{String.valueOf(codigo)});
            excluir = true;
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


}

