package com.example.de_cell;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import  android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase Decell) {
      Decell.execSQL("create table usuarios(numerocelular text primary key,nombre text, contraseña text )");
      Decell.execSQL("create table dinero(numerocelular text , dineros text)");
      Decell.execSQL("create table historial(numerocelular text , dineros text, destinatario text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
