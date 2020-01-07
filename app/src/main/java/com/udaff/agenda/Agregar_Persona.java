package com.udaff.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udaff.agenda.DAO.PersonaDBAdapter;

public class Agregar_Persona extends AppCompatActivity {


    private boolean existe(String nombre) {
        PersonaDBAdapter conexion = new PersonaDBAdapter(Agregar_Persona.this, PersonaDBAdapter.DATABASE_NAME, null, 1);
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] parametros = {nombre};
        String[] campos = {PersonaDBAdapter.COL_CELULAR};
        Cursor cursor = bd.query(PersonaDBAdapter.TABLE_PERSONA,
                campos,
                PersonaDBAdapter.COL_NOMBRE + "=?",
                parametros,
                null,
                null,
                null);
        return cursor.moveToFirst();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_persona);

        final EditText txt_nombre = findViewById(R.id.txt_nombre),
                txt_celular = findViewById(R.id.txt_celular),
                txt_direccion = findViewById(R.id.txt_direccion);


        Button btnRegistrar = findViewById(R.id.btnGuardar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identificador = txt_nombre.getText().toString(),
                        celular = txt_celular.getText().toString(),
                        direccion = txt_direccion.getText().toString();
                if (existe(identificador)) {
                    Toast.makeText(Agregar_Persona.this, " Contacto existente", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (identificador.isEmpty() || celular.isEmpty() || direccion.isEmpty()) return;
                ContentValues contentValues = new ContentValues();
                contentValues.put(PersonaDBAdapter.COL_NOMBRE, identificador);
                contentValues.put(PersonaDBAdapter.COL_CELULAR, celular);
                contentValues.put(PersonaDBAdapter.COL_DIRECCION, direccion);

                PersonaDBAdapter conexion = new PersonaDBAdapter(Agregar_Persona.this,
                        PersonaDBAdapter.DATABASE_NAME, null, 1);
                SQLiteDatabase bd = conexion.getWritableDatabase();
                long respuesta = bd.insert(PersonaDBAdapter.TABLE_PERSONA, null, contentValues);
                Toast.makeText(Agregar_Persona.this, "Se registro correctamete " + respuesta, Toast.LENGTH_SHORT).show();
                bd.close();
            }
        });
    }

}
