package com.example.lab3_2020_oa.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.example.lab3_2020_oa.models.User;
import com.example.lab3_2020_oa.models.VisitaDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ApiClient {
    private static final String PATH="http://192.168.1.119:45455/api/";
    private static  MyApiInterface myApiInteface;


    private static SharedPreferences sp;



    public static void guardarFoto(Context context, byte[] usuario){
        try {
            File archivo = new File (context.getFilesDir(), "foto.dat");
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(bo);

            oos.writeObject(usuario);

            bo.flush();
            fo.close();

            Toast.makeText(context, "foto almacenada", Toast.LENGTH_SHORT).show();

        } catch (IOException io) {
            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show();
        }
    }

    public static byte[] leerFoto(Context context){
        byte[] usuario = null;

        try {
            File archivo = new File (context.getFilesDir(), "foto.dat");
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(bi);

            usuario = (byte[]) ois.readObject();

            fi.close();

        } catch (IOException io) {
            Toast.makeText(context, "No se encontró el archivo", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException cnfe) {
            Toast.makeText(context, "No se encontró el objeto", Toast.LENGTH_LONG).show();
        }

        return usuario;
    }

    public static MyApiInterface getMyApiClient(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",retrofit.baseUrl().toString());
        return myApiInteface;
    }

    public static void guardarFirma(Context context, byte[] b) {
        try {
            File archivo = new File (context.getFilesDir(), "firma.dat");
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            ObjectOutputStream oos = new ObjectOutputStream(bo);

            oos.writeObject(b);

            bo.flush();
            fo.close();

            Toast.makeText(context, "firma almacenada", Toast.LENGTH_SHORT).show();

        } catch (IOException io) {
            Toast.makeText(context, "Error al guardar firma", Toast.LENGTH_LONG).show();
        }
        
    }

    public static byte[] leerFirma(Context context){
        byte[] firma = null;

        try {
            File archivo = new File (context.getFilesDir(), "firma.dat");
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi = new BufferedInputStream(fi);
            ObjectInputStream ois = new ObjectInputStream(bi);

            firma = (byte[]) ois.readObject();

            fi.close();

        } catch (IOException io) {
            Toast.makeText(context, "No se encontró el archivo", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException cnfe) {
            Toast.makeText(context, "No se encontró el objeto", Toast.LENGTH_LONG).show();
        }

        return firma;
    }

    public interface MyApiInterface {

        @POST("LoginApi/login")
        Call<String> login(@Body User user);


        @GET("visitas/misVisitas")
        Call<ArrayList<VisitaDTO>> misVisitas(@Header("Authorization") String token);

        @PUT("visitas")
        Call<VisitaDTO> actualizarVisita(@Header("Authorization")String token, @Body VisitaDTO v);

        @GET("visitas/{id}")
        Call<VisitaDTO> obtenerVisitaById(@Header("Authorization")  String token, @Path("id") long visitaId);

        //@GET("inmuebles")
        //Call<ArrayList<Propiedad>> obtenetPropiedades(@Header("Authorization")  String token);

        //@PUT("inmuebles")
        //Call<Propiedad> actualizarInmueble(@Header("Authorization")String token, @Body Propiedad p);

        //@POST("inmuebles")
        //Call<Propiedad> insertarInmueble(@Header("Authorization")String token, @Body Propiedad p);

        //@DELETE("inmuebles/{id}")
        //Call<Propiedad> eliminarInmueble(@Header("Authorization")  String token, @Path("id") int InmuebleId);



    }


}
