package com.example.de_cell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Historial extends AppCompatActivity {

    String sald;
    List<Linear> Lista;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        sald = getIntent().getStringExtra("key");
        init();
    }

    public void init() {


        Lista = new ArrayList<>();
        consulta();
        ListAdapter adapter = new ListAdapter(Lista, this);
        recyclerView = (RecyclerView) findViewById(R.id.recicle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        System.out.println("hoal");


    }

    private void consulta() {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
                null, 1);
        SQLiteDatabase Decell = admin.getWritableDatabase();

        try (Cursor cursor = Decell.rawQuery("select * from historial where numerocelular =" + sald + " ", null)) {

            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "si", Toast.LENGTH_SHORT).show();
                return;
            }
            do {
                Linear usuario = new Linear();
                usuario.setRemitente(cursor.getString(0));
                usuario.setSaldo(cursor.getString(1));
                usuario.setDestinatario(cursor.getString(2));
                Lista.add(usuario);
            } while (cursor.moveToNext());

        }
        Decell.close();


    }
}