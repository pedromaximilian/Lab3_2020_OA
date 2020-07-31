package com.example.lab3_2020_oa.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3_2020_oa.R;
import com.example.lab3_2020_oa.models.VisitaDTO;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<VisitaDTO> listaMutable;
    RecyclerView contenedor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        contenedor = view.findViewById(R.id.rvVisitasEmpleado);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        contenedor.setLayoutManager(layoutManager);

        homeViewModel.obtenerVisitas();
        listaMutable = new ArrayList<>();

        homeViewModel.getVisitas().observe(getViewLifecycleOwner(), new Observer<List<VisitaDTO>>() {
            @Override
            public void onChanged(List<VisitaDTO> visitaDTOS) {
                contenedor.setAdapter(new VisitasAdapter(visitaDTOS));
            }
        });










        return view;
    }
}
