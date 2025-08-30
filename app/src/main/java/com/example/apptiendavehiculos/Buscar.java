package com.example.apptiendavehiculos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Buscar extends AppCompatActivity {

    Button Buscar,Actualizar,Eliminar;
    EditText IdVehiculo,edtMarcaVehiculo,edtModeloVehiculo,edtColorVehiculo,edtPrecioVehiculo,edtPlacaVehiculo;
    RequestQueue requestQueue;
    private final String URL="http://192.168.18.43:3000/api/vehiculos/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadUI();
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarmarca();
            }
        });
        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }
    private void buscarmarca(){
        String marcaBuscar = IdVehiculo.getText().toString().trim();
        String urlFinal = URL + marcaBuscar;
        if(marcaBuscar.isEmpty()){
            Toast.makeText(this,"Ingrese Marca para Buscar",Toast.LENGTH_SHORT).show();
            return;
        }
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET,
                urlFinal,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String marca=jsonObject.getString("marca");
                            String modelo=jsonObject.getString("modelo");
                            String color=jsonObject.getString("color");
                            String precio=jsonObject.getString("precio");
                            String placa=jsonObject.getString("placa");

                            edtMarcaVehiculo.setText(marca);
                            edtModeloVehiculo.setText(modelo);
                            edtColorVehiculo.setText(color);
                            edtPrecioVehiculo.setText(precio);
                            edtPlacaVehiculo.setText(placa);

                            Toast.makeText(getApplicationContext(),"marca "+marca,Toast.LENGTH_SHORT).show();
                        }catch (Exception error){
                            Log.e("Error en Json", error.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // se importa la clase Log
                        Log.e("Error en el servicio", volleyError.toString());
                    }
                }
        );
        //3. Ejecutar la solicitud
        requestQueue.add(jsonObjectRequest);
    }
    private void update() {

        String id = IdVehiculo.getText().toString().trim();
        String URLFINAL = URL + id;
        if (id.isEmpty()) {
            Toast.makeText(this, "Ingrese datos para Actualizar", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject datos=new JSONObject();
            datos.put("marca",edtMarcaVehiculo.getText().toString().trim());
            datos.put("modelo",edtModeloVehiculo.getText().toString().trim());
            datos.put("color",edtColorVehiculo.getText().toString().trim());
            datos.put("precio",edtPrecioVehiculo.getText().toString().trim());
            datos.put("placa",edtPlacaVehiculo.getText().toString().trim());
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(

            Request.Method.PUT,
            URLFINAL,
            datos,
            new Response.Listener<JSONObject>() {
            @Override
                        public void onResponse(JSONObject jsonObject) {
                            try {
                                String marca=jsonObject.getString("marca");
                                String modelo=jsonObject.getString("modelo");
                                String color=jsonObject.getString("color");
                                String precio=jsonObject.getString("precio");
                                String placa=jsonObject.getString("placa");

                                edtMarcaVehiculo.setText(marca);
                                edtModeloVehiculo.setText(modelo);
                                edtColorVehiculo.setText(color);
                                edtPrecioVehiculo.setText(precio);
                                edtPlacaVehiculo.setText(placa);

                                Toast.makeText(getApplicationContext(),"marca "+marca,Toast.LENGTH_SHORT).show();
                            }catch (Exception error){
                                Log.e("Error al actualizar", error.toString());
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("Error al actualizar",volleyError.toString());
                        }
                    }
        );
            Toast.makeText(getApplicationContext(), "Vehículo Actualizado correctamente", Toast.LENGTH_SHORT).show();

            requestQueue.add(jsonObjectRequest);
        }catch (Exception error){
            Log.e("Error creado Json",error.toString());
        }

    }

    private void delete(){
        String id=IdVehiculo.getText().toString().trim();
        String URLFINAL=URL+id;
        if(id.isEmpty()){
            Toast.makeText(this,"Ingrese id para eliminar",Toast.LENGTH_SHORT).show();
            return;
        }
        requestQueue=Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.DELETE,
                URLFINAL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            edtMarcaVehiculo.setText("");
                            edtModeloVehiculo.setText("");
                            edtColorVehiculo.setText("");
                            edtPrecioVehiculo.setText("");
                            edtPlacaVehiculo.setText("");
                            Toast.makeText(getApplicationContext(), "Vehículo eliminado correctamente", Toast.LENGTH_SHORT).show();

                        }catch (Exception error){
                            Log.e("Error Json",error.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("Error al Eliminar", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
    private void loadUI(){
        //Text busqueda(marca)
        Buscar=findViewById(R.id.BtnBuscarVehiculo);

        //Campos que se van a llenar
        edtMarcaVehiculo=findViewById(R.id.edtMarcaVehiculo);
        edtModeloVehiculo=findViewById(R.id.edtModeloVehiculo);
        edtColorVehiculo=findViewById(R.id.edtColorVehiculo);
        edtPrecioVehiculo=findViewById(R.id.edtPrecioVehiculo);
        edtPlacaVehiculo=findViewById(R.id.edtPlacaVehiculo);

        //Botones
        IdVehiculo=findViewById(R.id.edtVehiculoBuscar);
        Actualizar=findViewById(R.id.BtnActualizarVehiculo);
        Eliminar=findViewById(R.id.BtnEliminarVehiculo);

    }
}