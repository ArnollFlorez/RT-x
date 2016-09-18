package com.example.lenovo.rt_x;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 16/09/2016.
 */
public class decodificador extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG ="RX" ;
    private String a="0000";
    private String b="0011";
    private String c="0101";
    private String d="0110";
    private String e="1001";
    private String f="1010";
    private String g="1100";
    private String h="1111";
    private String salto="XXXX"; //Se codifica como las dos luces encendidas

    public String Vector_Datos="";
    public int i=0; //contador de datos correctos

    TextView BitsFinales;
    TextView BitsIntemedios;
    boolean BanderaTexto=true;
    boolean FinCelda=true;
    private MediaPlayer sonido_error;
    private ColorStateList ColorBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decodificador);

        BitsFinales=(TextView)findViewById(R.id.textView_DatoFinal);
        BitsIntemedios=(TextView)findViewById(R.id.textView_Generado);

        ColorBase=BitsIntemedios.getTextColors();

    }

    @Override
    public void onClick(View v) {
        if(BanderaTexto){
            BitsIntemedios.setText("");
            BitsFinales.setText("");
            BanderaTexto=false;
        }

        switch (v.getId()){
            case R.id.button_0:
                EscrituradeBits("0",v);
                break;
            case R.id.button_1:
                EscrituradeBits("1",v);
                break;

            case R.id.button_ElimDeco:
                if(BitsFinales.length()!=0){
                    BitsFinales.setText(BitsFinales.getText().subSequence(0, BitsFinales.length() - 4));
                }else{
                    Toast.makeText(this, "Accion Invalida", Toast.LENGTH_SHORT).show();
                    Vibrator vibracion = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibracion.vibrate(200);
                }
                break;


            case R.id.button_verImagen:
                Snackbar.make(v,"Recurso en Construcción", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                VerImagen();

                break;

        }

    }

    public void VerImagen(){
        Intent intent = new Intent(this,matriz.class);
        intent.putExtra("VECTOR",Vector_Datos);
        startActivity(intent);
        Log.i("RX", "VerImagen: "+ Vector_Datos );
    }

    public void FuncionSalto(View v) {
        if (BitsIntemedios.getText().equals("Ultimos Bits Registrados") || BitsIntemedios.length()==4) {
            EscrituradeBits(salto, v);
        } else {
            Toast.makeText(this, "Accion Invalida",Toast.LENGTH_SHORT).show();
            Vibrator vibracion = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibracion.vibrate(200);
        }
    }

    public void EscrituradeBits(String x,  View v){
        if(FinCelda){BitsIntemedios.setText(x); FinCelda=false; }else{BitsIntemedios.append(x);}
        GenerarDato(v);

        Log.i(TAG, "EscrituradeBits: escribe el bit "+ x );
    }
    public void GenerarDato(View v){
        String captura = BitsIntemedios.getText().toString();

        BitsIntemedios.setTextColor(ColorBase);

        if(captura.length()==4){
            Log.i(TAG, "GenerarDato: verifico 4 ");
          if(DatoIngresadoFull(captura,a)||DatoIngresadoFull(captura,b)||DatoIngresadoFull(captura,c)||
                  DatoIngresadoFull(captura,d)||DatoIngresadoFull(captura,e)||DatoIngresadoFull(captura,f)||
                  DatoIngresadoFull(captura,g)||DatoIngresadoFull(captura,h)||DatoIngresadoFull(captura,salto)){
              Log.i(TAG, "GenerarDato: Todo esta bien");
          }

             else{
              Log.i(TAG, "GenerarDato: Hubo error");
                BitsIntemedios.setTextColor(Color.RED);
                Snackbar.make(v, "Hay un Error en esta Transmisión", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                Vibrator vibracion = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibracion.vibrate(300);
                sonido_error= MediaPlayer.create(this, R.raw.error);
                sonido_error.start();
            }

             FinCelda=true;
        }

    }

    public boolean DatoIngresadoFull(String xCaptura,String x){
        if (xCaptura.equals(x)){
            BitsFinales.append(xCaptura);
            Vector_Datos=Vector_Datos+x;
            i++;
            return true;
            }
        return false;
    }

}
