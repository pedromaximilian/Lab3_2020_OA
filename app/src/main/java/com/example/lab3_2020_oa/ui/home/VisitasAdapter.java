package com.example.lab3_2020_oa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3_2020_oa.R;
import com.example.lab3_2020_oa.models.VisitaDTO;

import java.util.List;

public class VisitasAdapter extends RecyclerView.Adapter<VisitasAdapter.ViewHolder> {

    List<VisitaDTO> listaVisitas;

    public VisitasAdapter(List<VisitaDTO> listaVisitas){
        this.listaVisitas = listaVisitas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_visita, parent, false);
        return  new ViewHolder(view, listaVisitas);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitasAdapter.ViewHolder holder, int position) {
        holder.cargaVisita(listaVisitas.get(position));

    }

    @Override
    public int getItemCount() {
        return listaVisitas.size();
    }


    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvPaciente, tvDireccion, tvPrestacion;
        Button btnDetalleVisita;
        List<VisitaDTO> lista;

        public ViewHolder(@NonNull View itemView, List<VisitaDTO> lista) {
            super(itemView);
            tvPaciente = itemView.findViewById(R.id.tvPaciente);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvPrestacion = itemView.findViewById(R.id.tvPrestacion);
            btnDetalleVisita = itemView.findViewById(R.id.btnDetalleVisita);
            btnDetalleVisita.setOnClickListener(this);
            this.lista = lista;

        }

        public void  cargaVisita(VisitaDTO v){
            tvPaciente.setText(v.getPaciente().getApellido()+ ", " +v.getPaciente().getNombre());
            tvDireccion.setText(v.getPaciente().getDireccion());
            tvPrestacion.setText(v.getItemDTO().getPrestacionNombre());
        }


        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            VisitaDTO seleccionada = lista.get(position);

            Toast.makeText(v.getContext(), seleccionada.getPaciente().getApellido(), Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putLong("visitaId", seleccionada.getVisitaId());
            bundle.putString("paciente", seleccionada.getPaciente().getApellido()+", "+seleccionada.getPaciente().getNombre());

            Navigation.findNavController(v).navigate(R.id.detalleFragment, bundle);

        }
    }
}
