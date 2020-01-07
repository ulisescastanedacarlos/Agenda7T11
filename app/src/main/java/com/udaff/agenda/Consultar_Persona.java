package com.udaff.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.udaff.agenda.DAO.PersonaDBAdapter;

public class Consultar_Persona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_persona);


        final EditText etNomBusqueda = findViewById(R.id.etNomBusqueda),
                etMostrarCelular = findViewById(R.id.etMostrarCelular),
                etMostrarDireccion = findViewById(R.id.etMostrarDireccion);

        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NomBusqueda = etNomBusqueda.getText().toString();
                if (NomBusqueda.isEmpty()) return;
                PersonaDBAdapter conexion = new PersonaDBAdapter(Consultar_Persona.this, PersonaDBAdapter.DATABASE_NAME, null, 1);
                SQLiteDatabase bd = conexion.getReadableDatabase();
                String[] parametros = {NomBusqueda};
                String[] campos = {PersonaDBAdapter.COL_CELULAR, PersonaDBAdapter.COL_DIRECCION};
                Cursor cursor = bd.query(PersonaDBAdapter.TABLE_PERSONA,
                        campos,
                        PersonaDBAdapter.COL_NOMBRE + "=?",
                        parametros,
                        null,
                        null,
                        null);
                if (!cursor.moveToFirst()) {
                    Toast.makeText(Consultar_Persona.this, "El nombre no existe", Toast.LENGTH_SHORT).show();
                    etMostrarCelular.setText("");
                    etMostrarDireccion.setText("");
                    bd.close();
                    return;
                }

                etMostrarCelular.setText(cursor.getString(cursor.getColumnIndex(PersonaDBAdapter.COL_CELULAR)));
                etMostrarDireccion.setText(cursor.getString(cursor.getColumnIndex(PersonaDBAdapter.COL_DIRECCION)));

            }
        });

    }
}
