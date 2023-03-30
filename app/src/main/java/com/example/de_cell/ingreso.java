package com.example.de_cell;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ingreso extends AppCompatActivity implements shimpentsAdapter.OnItemClickListener  {
    TextView bienvenido;
    List<shimpmentsElements> elementos;
    String celular;


    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
            null, 1);
    SQLiteDatabase Decell;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        bienvenido = findViewById(R.id.bienvenido);
        celular = getIntent().getExtras().getString("key");
        bienvenido.setText("Bienvenido, " + nombre(celular));
        init();


    }

    public void init() {
        elementos = new ArrayList<>();
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "6000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "12000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "20000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "56000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "130000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "235000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "340000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "445000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "545000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "645000"));
        elementos.add(new shimpmentsElements("#140f07", "Plan a enviar", "745000"));
        shimpentsAdapter ShimpentsAdapter = new shimpentsAdapter(elementos, this,this);
        RecyclerView recyclerView = findViewById(R.id.recyclerviewIngreso);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(ShimpentsAdapter);


    }
    public void moverdescripcion(shimpmentsElements item){
     Intent intent = new Intent(this, compra.class);
     intent.putExtra("cargost",item.getCargo());
     intent.putExtra("key", celular);
     startActivity(intent);


    }

    public String nombre(String key) {
        Decell = admin.getWritableDatabase();
        Cursor cursor = Decell.rawQuery("select nombre from usuarios where numerocelular ='" + key + "' ", null);
        String nombre = "";
        if (cursor.moveToFirst()) {

            nombre = cursor.getString(0);
            cursor.close();

        }

        return nombre;
    }

    @Override
    public void onBackPressed() {
    }

    public void salir2(View v) {
        finish();

    }

    public void historial(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
                null, 1);
        SQLiteDatabase Decell = admin.getWritableDatabase();
        try (Cursor cursor = Decell.rawQuery("select * from historial where numerocelular =" + celular + " ", null)) {

            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "No se encontró ningún historial de transacciones", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, Historial.class);
            intent.putExtra("key", celular);
            startActivity(intent);


        }
        Decell.close();


    }


    @Override
    public void onItemClick(shimpmentsElements item) {
        moverdescripcion(item);
    }
}
