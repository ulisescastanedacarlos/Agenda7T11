package com.udaff.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.udaff.agenda.Bean.Persona;
import com.udaff.agenda.DAO.PersonaDBAdapter;

import java.util.ArrayList;

public class Lista_Persona extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_persona);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPersonas);

        PersonaDBAdapter conexion = new PersonaDBAdapter(Lista_Persona.this, PersonaDBAdapter.DATABASE_NAME, null, 1);
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] campos = {PersonaDBAdapter.COL_NOMBRE, PersonaDBAdapter.COL_CELULAR, PersonaDBAdapter.COL_DIRECCION};
        Cursor cursor = bd.query(PersonaDBAdapter.TABLE_PERSONA,
                campos,
                null,
                null,
                null,
                null,
                null);
        if (!cursor.moveToFirst()) {
            cursor.close();
            bd.close();
            return;
        }

        ArrayList<Persona> personas = new ArrayList<>();


        do {
            String nombre = cursor.getString(cursor.getColumnIndex(PersonaDBAdapter.COL_NOMBRE));
            String celular = cursor.getString(cursor.getColumnIndex(PersonaDBAdapter.COL_CELULAR));
            String direccion = cursor.getString(cursor.getColumnIndex(PersonaDBAdapter.COL_DIRECCION));
            Persona persona = new Persona(nombre,celular,direccion);
            personas.add(persona);
        } while (cursor.moveToNext());
        // Fin del ciclo. Cerramos cursor

        cursor.close();


        AdaptadorPersonas adaptadorPersonas = new AdaptadorPersonas(personas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorPersonas);

    }
}
