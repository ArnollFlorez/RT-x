package com.example.lenovo.rt_x;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

public class matriz extends AppCompatActivity {
    private static final String TAG ="RX" ;
    private GridLayout matrizContenedor;
    private boolean TruncarColumnas=true;
    private int LongitudTrama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriz);

        matrizContenedor=(GridLayout)findViewById(R.id.matrizContenedora);

        Intent VectorIntent = getIntent();
        Bundle Info = VectorIntent.getExtras();
        String CadenaImagen=Info.getString("VECTOR").toString();
        Log.i("RX", "onCreate: " + CadenaImagen);

        int codEnviados=CadenaImagen.length();
        int Fila=0;

        String Auxiliar;
        String Continuacion=CadenaImagen;

        Log.i(TAG, "Auxiliar " + Continuacion + " Tamaño " +codEnviados);

        for(int i=0; i<(codEnviados/4);i++) {

            int n = 0;

            Auxiliar = Continuacion.subSequence(0, 4).toString();
            Continuacion = Continuacion.subSequence(4, Continuacion.length()).toString();

            if (Auxiliar.equals("XXXX")) {
                Fila++;
                if (TruncarColumnas) {
                    LongitudTrama = i;
                    TruncarColumnas = false;
                }

            } else {
                AgragandoImagen(Fila, i, Auxiliar);
            }

        }
        matrizContenedor.setColumnCount(LongitudTrama);

           /* if (Auxiliar.equals("XXXX")) {
                    n++;
                    matrizContenedor.setColumnCount(ValidarLongitud(i,n,matrizContenedor));
                    Fila++;

            } else {
                AgragandoImagen(Fila, i, Auxiliar);
            }*/
    }

    public void ValidarLongitud(int i, int Filas, View v){

        if(i/Filas != LongitudTrama){
            Snackbar.make(v, "Hay un Error en Longitud de Trama",Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show();
            Vibrator vibracion = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibracion.vibrate(100);
            MediaPlayer sonido_error = MediaPlayer.create(this, R.raw.error);
            sonido_error.start();
            matrizContenedor.setVisibility(View.GONE);
        }else {
            matrizContenedor.setColumnCount(LongitudTrama);
        }

    }


    public void AgragandoImagen(int Fila, int Columna,String Bits){

        Log.i(TAG, "AgragandoImagen: Fila " + Fila + "  Columna " + Columna + "  Dato " + Bits);

        ImageView Imagen = new ImageView(this);

        switch (Bits){
            case"0000":
                Imagen.setImageResource(R.drawable.a);
                break;
            case"0011":
                Imagen.setImageResource(R.drawable.b);
                break;
            case"0101":
                Imagen.setImageResource(R.drawable.c);
                break;
            case"0110":
                Imagen.setImageResource(R.drawable.d);
                break;
            case"1001":
                Imagen.setImageResource(R.drawable.e);
                break;
            case"1010":
                Imagen.setImageResource(R.drawable.f);
                break;
            case"1100":
                Imagen.setImageResource(R.drawable.g);
                break;
            case"1111":
                Imagen.setImageResource(R.drawable.h);
                break;

        }
        matrizContenedor.addView(Imagen,60,60);

    }



}
