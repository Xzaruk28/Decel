package com.example.de_cell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Registro extends AppCompatActivity {

    EditText numerocuenta;
    EditText nomresito;
    EditText contraseña;
    int diner = 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        numerocuenta = findViewById(R.id.numerocuenta);
        nomresito = findViewById(R.id.nomresito);
        contraseña = findViewById(R.id.contraseña);
    }
    public boolean validar() {

        boolean retorno = true;
        String tel = numerocuenta.getText().toString();
        String con = contraseña.getText().toString();
        String nom = nomresito.getText().toString();

        if (tel.isEmpty()) {
            numerocuenta.setError("Este campo esta vacio");
            retorno = false;
        }
        if (nom.isEmpty()) {
            nomresito.setError("Este campo esta vacio");
            retorno = false;
        }
        if (con.isEmpty()) {
            contraseña.setError("Este campo esta vacio");
            retorno = false;
        }
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
                null, 1);
        SQLiteDatabase Decell = admin.getWritableDatabase();
        Cursor c = Decell.rawQuery("select numerocelular from usuarios where numerocelular='" + tel + "'", null);
        if (c.moveToFirst()) {
            numerocuenta.setError("Este Usuario ya existe por favor intente con otro");
            c.close();
            Decell.close();
            retorno = false;
        }
        if (numerocuenta.getText().toString().length() < 10) {

            Toast.makeText(this, "El mínimo  de caracteres  en el campo usuario es de 10 caracteres", Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        if (contraseña.getText().toString().length() < 4) {

            Toast.makeText(this, "El mínimo  de caracteres en el campo contraseña es de 4 caracteres", Toast.LENGTH_SHORT).show();
            retorno = false;
        }
        return retorno;
    }
    public void registrarme(View v) {

        if (validar()) {
            registraUsuarios();
            registrodinero();
        }
    }
    public void registrodinero() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro", null, 1);
        SQLiteDatabase Decell = admin.getReadableDatabase();

        int din = diner;
        String tel = numerocuenta.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("dineros", din);
        cv.put("numerocelular", tel);
        Decell.insert("dinero", null, cv);

        Decell.close();
    }
    public void registraUsuarios() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro", null, 1);
        SQLiteDatabase Decell = admin.getReadableDatabase();
        String tel = numerocuenta.getText().toString();
        String con = contraseña.getText().toString();
        String nom = nomresito.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("numerocelular", tel);
        cv.put("nombre", nom);
        cv.put("contraseña", con);

        Decell.insert("usuarios", null, cv);
        Decell.close();
        finish();
    }
}