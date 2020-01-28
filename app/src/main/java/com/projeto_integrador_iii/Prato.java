package com.projeto_integrador_iii;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Prato {
    private int codigo;
    private String nomeprato;
    private String descricao;
    private int quantidade;

    public int getCodigo() {return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }


    public String getNomeprato() { return nomeprato; }

    public void setNomeprato(String nomeprato) { this.nomeprato = nomeprato; }


    public String getDescricao() { return descricao;}

    public void setDescricao(String descricao) {this.descricao=descricao;}


    public int getQuantidade() {return quantidade;}

    public void setQuantidade(int quantidade) {this.quantidade=quantidade;}
}

