package com.example.lab3_2020_oa.ui.visita;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab3_2020_oa.R;
import com.github.gcacace.signaturepad.views.SignaturePad;

public class firma extends Fragment {

    private VisitaViewModel vm;
    SignaturePad signaturePad;
    Button saveButton, clearButton;
    long i;
    String nombre;

    public static firma newInstance() {
        return new firma();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(VisitaViewModel.class);
        View view = inflater.inflate(R.layout.firma_fragment, container, false);

        try {
            signaturePad = (SignaturePad)view.findViewById(R.id.signaturePad);
            saveButton = (Button)view.findViewById(R.id.saveButton);
            clearButton = (Button)view.findViewById(R.id.clearButton);
            saveButton.setEnabled(false);
            clearButton.setEnabled(false);
            i = getArguments().getLong("visitaId");
            nombre = getArguments().getString("nombre");

            //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
                @Override
                public void onStartSigning() {

                }

                @Override
                public void onSigned() {
                    saveButton.setEnabled(true);
                    clearButton.setEnabled(true);
                }

                @Override
                public void onClear() {
                    saveButton.setEnabled(false);
                    clearButton.setEnabled(false);
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //write code for saving the signature here
                    Bitmap b = signaturePad.getTransparentSignatureBitmap();

                    vm.guardarFirma(b, v, i, nombre);



                    Toast.makeText(getContext(), "Firma Guardada", Toast.LENGTH_SHORT).show();
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signaturePad.clear();
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


        return  view;
    }



}
