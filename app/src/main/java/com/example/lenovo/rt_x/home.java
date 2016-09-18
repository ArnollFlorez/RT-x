package com.example.lenovo.rt_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Lenovo on 16/09/2016.
 */
public class home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.code:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.deco:
                Intent intent2 = new Intent(this,decodificador.class);
                startActivity(intent2);
                break;


        }
    }
}
