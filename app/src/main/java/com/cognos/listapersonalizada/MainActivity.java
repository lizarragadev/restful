package com.cognos.listapersonalizada;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Item> listaElementos;
    private Item item;
    private ItemAdapter adaptador;
    private AlertDialog ad;

    /**
     *
     * /data/data/com.cognos.listapersonalizada/files/datos.txt
     *
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.listaElementos);
        listaElementos = new ArrayList<Item>();

        item = new Item(getResources().getDrawable(R.drawable.ic_person_black_36dp),
                    "Titulo Uno", "Subtitulo Lorem Ipsum");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_alarm_off_black_36dp),
                "Titulo Dos","Subtitulo Lorem Ipsum");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_card_giftcard_black_36dp),
                "Titulo tres","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_alarm_off_black_36dp),
                "Titulo Cuatro","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_card_giftcard_black_36dp),
                "Titulo Cinco","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_card_giftcard_black_36dp),
                "Titulo Seis","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_alarm_off_black_36dp),
                "Titulo Siete","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_person_black_36dp),
                "Titulo Ocho","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_alarm_off_black_36dp),
                "Titulo Nueve","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        item = new Item(getResources().getDrawable(R.drawable.ic_card_giftcard_black_36dp),
                "Titulo Diez","Subtitulo Lorem Ipsum ");
        listaElementos.add(item);

        adaptador = new ItemAdapter(listaElementos, this);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item it = listaElementos.get(i);

                Intent intent = new Intent(getApplicationContext(), InformacionActivity.class);
                intent.putExtra("titulo", it.getTitulo());
                intent.putExtra("subtitulo", it.getSubTitulo());
                //intent.putExtra("imagen", (Serializable) it.getImagen());

                startActivity(intent);
            }
        });

        registerForContextMenu(lista);

        ad = dialogoAlerta();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_uno:
                ad.show();
                return true;
            case R.id.menu_dos:
                dialogoPersonalidado().show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.menu_c_uno:
                Toast.makeText(getApplicationContext(),
                        "Hizo click en el item 1",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_c_dos:
                Toast.makeText(getApplicationContext(),
                        "Click en DOS",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_c_tres:
                Toast.makeText(getApplicationContext(),
                        "Contextual en 3",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public AlertDialog dialogoAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Advertencia")
                .setIcon(android.R.drawable.ic_menu_day)
                .setCancelable(false)
                .setMessage("Ocurrio un error en la obtencion de datos")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Click en dialogo",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Click en dialogo",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Click en dialogo",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }

    public AlertDialog dialogoPersonalidado(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_personalizado, null);

        builder.setView(view);

        Button btnAceptar = (Button)view.findViewById(R.id.btnAceptar);
        Button btnCancelar = (Button)view.findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Dialogo personalizado ACEPTAR",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarDialogo();
            }
        });
        return builder.create();
    }

    public void cerrarDialogo(){
        ad.dismiss();
    }

}



