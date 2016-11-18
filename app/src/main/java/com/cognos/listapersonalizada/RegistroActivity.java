package com.cognos.listapersonalizada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombres;
    private EditText etApellidos;
    private EditText etCorreo;
    private EditText etCi;
    private EditText etUsuario;
    private EditText etPassword;

    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombres = (EditText) findViewById(R.id.etNombres);
        etApellidos = (EditText) findViewById(R.id.etApellidos);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etCi = (EditText) findViewById(R.id.etCI);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnRegistrar = (Button) findViewById(R.id.btnAceptar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registra();
            }
        });

    }

    public void registra(){
        final String nombres = etNombres.getText().toString().trim();
        final String apellidos = etApellidos.getText().toString().trim();
        final String correo = etCorreo.getText().toString().trim();
        final String ci = etCi.getText().toString().trim();
        final String usuario = etUsuario.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        //pb.setIndeterminate(true);
        //pbLogin.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                "http://androidbolivia.com/cognos/addUser.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //pbLogin.setIndeterminate(false);
                            //pbLogin.setVisibility(View.INVISIBLE);
                            responseOk(response);
                        } catch (JSONException e){
                            System.out.println("ERROR EN RESPONSE");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //pbLogin.setIndeterminate(false);
                        //pbLogin.setVisibility(View.INVISIBLE);
                        //responseFail(error);
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombres", nombres);
                params.put("apellidos", apellidos);
                params.put("carnet", ci);
                params.put("correo", correo);
                params.put("usuario", usuario);
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
            //JSONObject aux = new JSONObject(resp.getString("status_add"));
            //System.out.println("RESULTADO: "+aux);
            if(resp.getString("status_add").toString().equals("1")){
                Toast.makeText(getApplicationContext(),
                        "Registro Correcto",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Registro Incorrecto",
                        Toast.LENGTH_SHORT).show();
            }
        }catch (Throwable e){
            System.out.println("No se pudo convertir");
        }
    }
}
