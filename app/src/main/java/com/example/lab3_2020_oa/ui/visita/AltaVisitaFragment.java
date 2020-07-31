package com.example.lab3_2020_oa.ui.visita;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab3_2020_oa.R;
import com.example.lab3_2020_oa.models.VisitaDTO;
import com.example.lab3_2020_oa.ui.gallery.GalleryViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AltaVisitaFragment extends Fragment {

    private VisitaViewModel mViewModel;
    Button btnGuardar, btnIrAFoto, btnIraFirma;
    TextView taNotaEmpleado, nombrePacienteAlta;
    View view;
    long i;
    String nombre;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        try {
            mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(VisitaViewModel.class);

        view = inflater.inflate(R.layout.alta_visita_fragment, container, false);
        btnGuardar = view.findViewById(R.id.btnGuardarVisita);
        btnIrAFoto = view.findViewById(R.id.btnFoto);
        btnIraFirma = view.findViewById(R.id.btnIrAFirma);
        taNotaEmpleado = view.findViewById(R.id.taNotaEmpleado);
        nombrePacienteAlta = view.findViewById(R.id.tvAltaNombrePaciente);


        i = getArguments().getLong("visitaId");
        nombre = getArguments().getString("nombre");
        mViewModel.obtenerVisitaById(i);





        mViewModel.getNotaEmpleado().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                taNotaEmpleado.setText(s);
            }
        });


        taNotaEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setNotaEmpleado(taNotaEmpleado.getText().toString());
            }
        });

            nombrePacienteAlta.setText(nombre);
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                        mViewModel.guardarVisita(taNotaEmpleado.getText().toString()+"", i);


                }catch (Exception e){
                    Log.d("Ex", e.getMessage());
                }


            }
        });

        btnIrAFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.foto_fragment);
            }
        });

        btnIraFirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putLong("visitaId", i);
                bundle.putString("nombre", nombre);
                Navigation.findNavController(view).navigate(R.id.firma, bundle);
            }
        });




        return view;
    }


}
