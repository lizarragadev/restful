package com.cognos.listapersonalizada;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btnIngresar;
    private ProgressBar pbLogin;

    private String username;
    private String password;

    private static String URL = "http://androidbolivia.com/cognos/login.php";

    //private String PREFERENCIAS = "mis_preferencias";
    //private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        pbLogin = (ProgressBar) findViewById(R.id.pbLogin);

        /*sharedPreferences = getSharedPreferences(PREFERENCIAS, 0);
        String usuario = sharedPreferences.getString("usuario", "");
        String password = sharedPreferences.getString("password", "");

        etUsuario.setText(usuario);
        etPassword.setText(password);*/

        /*String []archivos = fileList();
        if (existe(archivos, "datos.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("datos.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null){
                    todo = todo + linea +"\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                etUsuario.setText(todo);
            }catch (IOException e){

            }
        }*/

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valida();
                /*sharedPreferences = getSharedPreferences(PREFERENCIAS, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", etUsuario.getText().toString());
                editor.putString("password", etPassword.getText().toString());
                editor.commit();
                grabar();*/
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    public void valida(){
        if (etUsuario.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            esVacio();
        } else {
            login();
        }
    }

    public void login() {
        username = etUsuario.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        pbLogin.setIndeterminate(true);
        pbLogin.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       try {
                           pbLogin.setIndeterminate(false);
                           pbLogin.setVisibility(View.INVISIBLE);
                           responseOk(response);
                       } catch (JSONException e){
                           System.out.println("ERROR EN RESPONSE");
                       }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pbLogin.setIndeterminate(false);
                        pbLogin.setVisibility(View.INVISIBLE);
                        //responseFail(error);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", username);
                params.put("contrasenia", password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public void responseOk(String response) throws JSONException{
        try {
            JSONObject resp = new JSONObject(response);
            JSONObject aux = new JSONObject(resp.getString("user"));
            System.out.println("RESULTADO: "+aux);
            if(aux.getString("status").toString().equals("success")){
                Intent intent = new Intent(getApplicationContext(),
                        InformacionActivity.class);
                intent.putExtra("nom", aux.getString("nombres").toString());
                intent.putExtra("ape", aux.getString("apellidos").toString());
                intent.putExtra("ci", aux.getString("ci").toString());
                intent.putExtra("cor", aux.getString("correo").toString());
                startActivity(intent);

                Toast.makeText(getApplicationContext(),
                        "Correcto",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Incorrecto",
                        Toast.LENGTH_SHORT).show();
            }
        }catch (Throwable e){
            System.out.println("No se pudo convertir");
        }
    }

    public void esVacio() {
        Toast.makeText(getApplicationContext(),
                "Existen campos vacios",
                Toast.LENGTH_SHORT).show();
        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(500);
    }

    /*public boolean existe (String[] archivo, String buscaArchivo){
        for (int i = 0; i < archivo.length; i++) {
            if(buscaArchivo.equals(archivo[i]))
                return true;
        }
        return false;
    }

    public void grabar(){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(
                    openFileOutput("datos.txt", Activity.MODE_PRIVATE));
            archivo.write(etUsuario.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e){

        }
    }*/
}
