package com.example.lab3_2020_oa.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab3_2020_oa.models.VisitaDTO;
import com.example.lab3_2020_oa.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {


    private MutableLiveData<String> mText;
    private Context context;
    private List<VisitaDTO> miLista = new ArrayList<>();

    private MutableLiveData<List<VisitaDTO>> visitas;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        this.context = application.getApplicationContext();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<VisitaDTO>> getVisitas(){
        if (visitas==null){
            visitas = new MutableLiveData<>();
        }
        return visitas;
    }


    public void obtenerVisitas(){
        //envia el token y setea las visitas en el viewmodel
        SharedPreferences sp = context.getSharedPreferences("token", 0);

        String accesToken = sp.getString("token", "");

        Call<ArrayList<VisitaDTO>> dato= ApiClient.getMyApiClient().misVisitas(accesToken);

        dato.enqueue(new Callback<ArrayList<VisitaDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<VisitaDTO>> call, Response<ArrayList<VisitaDTO>> response) {
                if (response.isSuccessful()){
                    miLista = response.body();

                    visitas.postValue(miLista);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<VisitaDTO>> call, Throwable t) {

                Toast.makeText(context, "Error en Home View Model", Toast.LENGTH_SHORT).show();
            }
        });
    }
}