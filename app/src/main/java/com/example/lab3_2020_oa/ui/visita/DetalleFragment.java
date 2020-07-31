package com.example.lab3_2020_oa.ui.visita;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab3_2020_oa.R;
import com.example.lab3_2020_oa.models.VisitaDTO;

public class DetalleFragment extends Fragment {

    private VisitaViewModel mViewModel;
    Button altaVisita;
    TextView tvNombre, tvDireccion, tvPrestacion, tvNumero;
    long i;

    public static DetalleFragment newInstance() {
        return new DetalleFragment();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(VisitaViewModel.class);
        View view = inflater.inflate(R.layout.detalle_fragment, container, false);
        altaVisita = view.findViewById(R.id.btnAltaVisita);
        i = getArguments().getLong("visitaId");
        tvNombre = view.findViewById(R.id.tvDetallePacienteNombre);
        tvDireccion = view.findViewById(R.id.tvDetallePacienteDireccion);
        tvPrestacion = view.findViewById(R.id.tvDetallePrestacion);
        tvNumero = view.findViewById(R.id.tvDetalleNumero);

        mViewModel.obtenerVisitaById(i);


        altaVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putLong("visitaId", i);
                bundle.putString("nombre", tvNombre.getText().toString());
                Navigation.findNavController(v).navigate(R.id.altaVisitaFragment, bundle);
            }
        });

        mViewModel.getVisitaDTOLiveData().observe(getViewLifecycleOwner(), new Observer<VisitaDTO>() {
            @Override
            public void onChanged(VisitaDTO x) {
                tvNombre.setText(x.getPaciente().getApellido()+", "+x.getPaciente().getNombre());
                tvDireccion.setText(x.getPaciente().getDireccion());
                tvPrestacion.setText(x.getItemDTO().getPrestacionNombre());
                tvNumero.setText(x.getNumeroItem()+"");
            }
        });

        return view;
    }



}
