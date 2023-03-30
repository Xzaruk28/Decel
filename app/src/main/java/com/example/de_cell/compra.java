package com.example.de_cell;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
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

public class compra extends AppCompatActivity {

    EditText numero;
    TextView tv2;
    TextView cuenta;
    String sald;
    String cargo;
    private Button boton;
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
            null, 1);
    SQLiteDatabase Decell;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);
        tv2 = findViewById(R.id.tv2);
        cuenta = findViewById(R.id.cuenta);
        numero = findViewById(R.id.numero);
        cargo = getIntent().getStringExtra("cargost");
        tv2.setText("el valor a pagar es , " + cargo);

        sald = getIntent().getStringExtra("key");
        cuenta.setText("su saldo es : " + dineros(sald));
        boton = (Button) findViewById(R.id.confirmar);
        boton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(compra.this);

                alerta.setTitle("Confirmacion ")

                        .setMessage("Esta seguro de hacer el giro?")
                        .setCancelable(false)
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (validar()) {
                                    return;
                                }

                                suma();
                                resta();
                                historial();
                                finish();
                            }

                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                alerta.create().show();
            }
        });

    }

    public void historial() {
        Decell = admin.getWritableDatabase();
        String tel = sald;
        String des = numero.getText().toString();
        String dine = cargo;

        ContentValues cv = new ContentValues();
        cv.put("numerocelular", tel);
        cv.put("destinatario", des);
        cv.put("dineros", dine);

        Decell.insert("historial", null, cv);
        Decell.close();
    }

    public boolean validar() {
        String num = numero.getText().toString();
        float f_precio =Float.parseFloat((cargo)) ;
        float f_saldo = Float.parseFloat(dineros(sald));
        if (f_saldo < f_precio) {
            Toast.makeText(this, "EL saldo es Insuficiente ", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (num.isEmpty()) {
            numero.setError("Este campo esta vacio");
            return true;
        }
        if (num.equals(sald)) {
            Toast.makeText(this, "EL USUARIO DEBE SER DIFERENTE  ", Toast.LENGTH_SHORT).show();
            return true;
        }

        Decell = admin.getWritableDatabase();
        Cursor fila = Decell.rawQuery("select numerocelular from usuarios where numerocelular='" + num + "'", null);
        if (fila.moveToFirst()) {
            return false;
        } else {
            Toast.makeText(this, "EL USUARIO NO EXISTE  ", Toast.LENGTH_SHORT).show();
            return true;
        }
    }


    public String dineros(String key) {
        Decell = admin.getWritableDatabase();
        Cursor cursor = Decell.rawQuery("select dineros from dinero where numerocelular ='" + key + "' ", null);
        String dineros = "";
        if (cursor.moveToFirst()) {

            dineros = cursor.getString(0);
            cursor.close();
        }

        return dineros;
    }

    public void resta() {
        float f_precio = Float.parseFloat((cargo));
        float f_saldo = Float.parseFloat(dineros(sald));
        float resultado;

        resultado = f_saldo - f_precio;
        String resultante = Float.toString(resultado);

        ContentValues act = new ContentValues();
        act.put("dineros", resultante);
        int cantidad = Decell.update("dinero", act, "numerocelular=" + sald, null);

        Decell.close();

        if (cantidad == 1) {


        }
    }

    public void suma() {
        float f_precio = Float.parseFloat((cargo));
        float f_saldo = Float.parseFloat(dineros(sald));
        float resultado;
        String llave = numero.getText().toString();
        resultado = f_saldo + f_precio;
        String resultante = Float.toString(resultado);

        ContentValues act = new ContentValues();
        act.put("dineros", resultante);
        int cantidad = Decell.update("dinero", act, "numerocelular=" + llave, null);

        Decell.close();

        if (cantidad == 1) {

        }
    }

    public void cancelar(View v) {

        finish();

    }


}