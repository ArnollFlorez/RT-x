package com.example.lenovo.rt_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

public class matriz extends AppCompatActivity {
    private static final String TAG ="RX" ;
    private GridLayout matrizContenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriz);

        matrizContenedor=(GridLayout)findViewById(R.id.matrizContenedora);

        Intent VectorIntent = getIntent();
        Bundle Info = VectorIntent.getExtras();
        String CadenaImagen=Info.getString("VECTOR");
        Log.i("RX", "onCreate: " + CadenaImagen);

        int codEnviados=CadenaImagen.length();

        for(int i=0;i==codEnviados;i++){



        }




    }



}
