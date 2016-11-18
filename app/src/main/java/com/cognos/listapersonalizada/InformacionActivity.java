package com.cognos.listapersonalizada;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InformacionActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvSubTitulo;
    private ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        //String titulo = getIntent().getExtras().getString("titulo");
        //String subTitulo = getIntent().getExtras().getString("subtitulo");
        //Drawable imagen = (Drawable) getIntent().getExtras().getSerializable("imagen");

        String campo1 = getIntent().getExtras().getString("nom")+ " " +
                getIntent().getExtras().getString("ape");

        String campo2 = getIntent().getExtras().getString("ci")+ " - " +
                getIntent().getExtras().getString("cor");

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvSubTitulo = (TextView) findViewById(R.id.tvSubTitulo);
        //ivImagen = (ImageView) findViewById(R.id.ivImagen);

        tvTitulo.setText(campo1);
        tvSubTitulo.setText(campo2);

    }

    public void agregar(View v){
        startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
    }
}
