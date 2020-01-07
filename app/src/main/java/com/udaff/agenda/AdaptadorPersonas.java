package com.udaff.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udaff.agenda.Bean.Persona;

import java.util.List;

public class AdaptadorPersonas extends RecyclerView.Adapter<AdaptadorPersonas.MyViewHolder>{

    private List<Persona> listaDePersonas;

    public List<Persona> getListaDePersonas() {
        return listaDePersonas;
    }

    public void setListaDePersonas(List<Persona> listaDePersonas) {
        this.listaDePersonas = listaDePersonas;
    }

    public AdaptadorPersonas(List<Persona> listaDePersonas) {
        this.listaDePersonas = listaDePersonas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaPersona = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_persona, viewGroup, false);
        return new MyViewHolder(filaPersona);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
// Obtener la persona de nuestra lista gracias al índice i
        Persona persona = listaDePersonas.get(i);

        // Obtener los datos de la lista
        String celular = persona.getCelular();
        String direccion = persona.getDireccion();
        String identificador = String.valueOf(persona.getNombre());
        // Y poner a los TextView los datos con setText
        myViewHolder.tvCelular.setText(celular);
        myViewHolder.tvDireccion.setText(direccion);
        myViewHolder.tvIdentificador.setText(identificador);
    }

    @Override
    public int getItemCount() {
        return listaDePersonas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCelular, tvDireccion, tvIdentificador;

        MyViewHolder(View itemView) {
            super(itemView);
            this.tvCelular = itemView.findViewById(R.id.tvFilaCelular);
            this.tvDireccion = itemView.findViewById(R.id.tvFilaDireccion);
            this.tvIdentificador = itemView.findViewById(R.id.tvFilaNombre);
        }
    }

}
