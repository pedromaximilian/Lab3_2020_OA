package com.example.lab3_2020_oa.ui.visita;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.lab3_2020_oa.R;
import com.example.lab3_2020_oa.models.VisitaDTO;
import com.example.lab3_2020_oa.request.ApiClient;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class VisitaViewModel extends AndroidViewModel {

    private MutableLiveData<Bitmap> foto;
    private MutableLiveData<VisitaDTO> visitaDTOMutableLiveData;
    private MutableLiveData<String> notaEmpleado;




    public LiveData<String> getNotaEmpleado(){
        if (notaEmpleado==null){
            notaEmpleado = new MutableLiveData<>();
        }
        return notaEmpleado;
    }

    public void setNotaEmpleado(String n){
        notaEmpleado.setValue(n);
    }



    Context context;


    public LiveData<VisitaDTO> getVisitaDTOLiveData(){
        if (visitaDTOMutableLiveData==null){
            visitaDTOMutableLiveData = new MutableLiveData<>();
        }
        return visitaDTOMutableLiveData;
    }

    public void setVisitaDTOMutableLiveData(VisitaDTO visitaDTO){
        this.visitaDTOMutableLiveData.setValue(visitaDTO);
    }



    public VisitaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        visitaDTOMutableLiveData = new MutableLiveData<>();
        notaEmpleado = new MutableLiveData<>();

    }

    public LiveData<Bitmap> getFoto(){
        if(foto==null){
            foto=new MutableLiveData<>();
        }
        return foto;
    }

    public void setFoto(Bitmap f){
        foto.postValue(f);
    }






    public void respuetaDeCamara(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE){

        try {
            Log.d("salida",requestCode+"");
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                //Recupero los datos provenientes de la camara.
                Bundle extras = data.getExtras();
                //Casteo a bitmap lo obtenido de la camara.
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                //Rutina para optimizar la foto,
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
                foto.setValue(imageBitmap);
                //Rutina para convertir a un arreglo de byte los datos de la imagen
                byte [] b=baos.toByteArray();
                Log.d("salida",b.length+"");
                ApiClient.guardarFoto(context, b);




                //Aquí podría ir la rutina para llamar al servicio que recibe los bytes.
            }
        }catch (Exception e){
            Log.d("Ex",e.getMessage()+"");

        }


    }

    public void guardarVisita(String areaText, long i) {
        try {
            SharedPreferences sp = context.getSharedPreferences("token", 0);
            String accesToken = sp.getString("token", "");

            obtenerVisitaById(i);

            VisitaDTO v1 = new VisitaDTO();

            v1 = visitaDTOMutableLiveData.getValue();
            v1.setFoto(ApiClient.leerFoto(context));
            v1.setFirma(ApiClient.leerFirma(context));
            v1.setNotaEmpleado(areaText);





            Call<VisitaDTO> dato = ApiClient.getMyApiClient().actualizarVisita(accesToken, v1);
            dato.enqueue(new Callback<VisitaDTO>() {
                @Override
                public void onResponse(Call<VisitaDTO> call, Response<VisitaDTO> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "Todo salio bien", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<VisitaDTO> call, Throwable t) {

                    Toast.makeText(context,  t.getMessage()+" No te rindas!!!!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Log.d("Ex", e.getMessage());
        }




    }

    public void guardarFirma(Bitmap imageBitmap, View v, long i, String nombre) {

        try {
            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            //Rutina para convertir a un arreglo de byte los datos de la imagen
            byte [] b=baos.toByteArray();
            Log.d("salida",b.length+"");
            ApiClient.guardarFirma(context, b);
            Bundle bundle = new Bundle();
            bundle.putLong("visitaId", i);
            bundle.putString("nombre", nombre);
            Navigation.findNavController(v).navigate(R.id.altaVisitaFragment, bundle);
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void obtenerVisitaById(long i) {
        try {
            SharedPreferences sp = context.getSharedPreferences("token", 0);

            String accesToken = sp.getString("token", "");
            Call<VisitaDTO> dato = ApiClient.getMyApiClient().obtenerVisitaById(accesToken, i);

            dato.enqueue(new Callback<VisitaDTO>() {
                @Override
                public void onResponse(Call<VisitaDTO> call, Response<VisitaDTO> response) {
                    if (response.isSuccessful()){
                        try {
                            visitaDTOMutableLiveData.setValue(response.body());

                            String r = response.body().toString();
                        }catch (Exception e){
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<VisitaDTO> call, Throwable t) {

                    Log.d("s", t.getMessage());

                }
            });
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }



}
