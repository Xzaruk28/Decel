package com.example.de_cell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText numerocuenta;
    EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        numerocuenta = findViewById(R.id.numerocuenta);
        contraseña = findViewById(R.id.contraseña);
    }
    public void ingreso(View v) {

        if (validar()) {
            consultar();
        }
    }
    public void consultar() {
        String celular = numerocuenta.getText().toString();
        String clave = contraseña.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "registro",
                null, 1);
        SQLiteDatabase Decell = admin.getWritableDatabase();

        Cursor fila = Decell.rawQuery("select * from usuarios where numerocelular='" + celular +
                "' and contraseña='" + clave + "'", null);
        if (fila.moveToFirst()) {
            fila.close();
            Decell.close();

            Intent intent = new Intent(this, ingreso.class);
            intent.putExtra("key", celular);
            startActivity(intent);
        } else {
            Toast.makeText(this, "EL USUARIO NO EXISTE POR FAVOR CORRIJA O REGÍSTRESE EL USUARIO ", Toast.LENGTH_SHORT).show();
        }
        limpiar();
    }
    private void limpiar() {
        numerocuenta.setText("");
        contraseña.setText("");
    }

    public void registro(View v) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
    public boolean validar() {
        boolean retorno = true;
        String tel = numerocuenta.getText().toString();
        String con = contraseña.getText().toString();

        if (tel.isEmpty()) {
            numerocuenta.setError("Este campo esta vacio");
            retorno = false;
        }
        if (con.isEmpty()) {
            contraseña.setError("Este campo esta vacio");
            retorno = false;
        }
        return retorno;
    }
}