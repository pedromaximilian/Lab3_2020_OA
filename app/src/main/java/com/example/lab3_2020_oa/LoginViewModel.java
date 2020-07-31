package com.example.lab3_2020_oa;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.lab3_2020_oa.models.User;
import com.example.lab3_2020_oa.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    MutableLiveData<String> error;
    MutableLiveData<String> exito;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        error = new MutableLiveData<>();
        exito = new MutableLiveData<>();
        context = application.getApplicationContext();
    }


    public LiveData<String> getError() {
        if (error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public LiveData<String> getExito() {
        if (exito == null){
            exito = new MutableLiveData<String>();
        }
        return exito;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void validar(String mail, String pass){
        User user = new User(mail, pass);

        Call<String> dato= ApiClient.getMyApiClient().login(user);

        dato.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();

                if (token != null){
                    SharedPreferences sp = context.getSharedPreferences("token", 0);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putString("token", "Bearer " + token);
                    editor.commit();
                    exito.postValue("1");
                }else{
                    error.setValue("Verifique usuario y contraseña");
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                error.setValue("Posible error de conexión");
            }
        });
/*
        if (mail.equals("")){
            error.setValue("Verifique usuario y contraseña");
        }else{
            Usuario u = new Usuario(1, "29887502", "Lucero", "Pedro", "2664565685", "pedro@mail.com", "123");
            usuario.setValue(u);
        }
*/


    }
}
