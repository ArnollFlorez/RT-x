package com.example.lenovo.rt_x;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="RX" ;

    /*Codificacion de cada Imagen */

    private String a="0000";
    private String b="0011";
    private String c="0101";
    private String d="0110";
    private String e="1001";
    private String f="1010";
    private String g="1100";
    private String h="1111";
    private String salto="XXXX"; //Se codifica como las dos luces encendidas


    Button Btsalto;
    Button Btborrar;
    TextView TvCodigo;
    TextView TvCodigoActual;
    boolean BanderaTexto=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Btsalto=(Button)findViewById(R.id.buttonSalto);
        Btborrar=(Button)findViewById(R.id.botton_Eliminar);
        TvCodigo=(TextView)findViewById(R.id.tvCodigo);
        TvCodigoActual=(TextView)findViewById(R.id.tvCodigoActual);

        Log.i(TAG, "onClick: "+ a);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick:funciona");
        if(BanderaTexto){
            TvCodigo.setText("");
            TvCodigoActual.setText("");
            BanderaTexto=false;
        }

        switch (v.getId()){

            case R.id.imageButtona:
               Rellenar(a);
                Log.i(TAG, "onClick: "+a);
                    break;
            case R.id.imageButtonb:
                Rellenar(b);

                break;
            case R.id.imageButtonc:
                Rellenar(c);
                break;
            case R.id.imageButtond:
                Rellenar(d);
                break;
            case R.id.imageButtone:
                Rellenar(e);
                break;
            case R.id.imageButtonf:
                Rellenar(f);
                break;
            case R.id.imageButtong:
                Rellenar(g);
                break;
            case R.id.imageButtonh:
                Rellenar(h);
                break;
            case R.id.botton_Eliminar:
                Snackbar.make(v, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.buttonSalto:
                Rellenar(salto);
                break;
     }
    }

    public void Rellenar(String x){
        TvCodigo.append(x);
        TvCodigoActual.setText(x);
    }
}
