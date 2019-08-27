package com.example.dnireniec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {
    Button btn_consulta;
    EditText ext_dni;
    TextView txt_name;
    String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_name=findViewById(R.id.txt_name);
        ext_dni=findViewById(R.id.ext_dni);
        btn_consulta=findViewById(R.id.btn_consulta);
        btn_consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_name.setText(ConsultaDni(ext_dni.getText().toString()));
                //txt_name.setText(ext_dni.getText());
            }
        });
    }
    public String ConsultaDni (String dni) {
        String a="";
        try {
            URL url = new URL("http://aplicaciones007.jne.gob.pe/srop_publico/Consulta/Afiliado/GetNombresCiudadano?DNI="+dni);
            URLConnection con = url.openConnection();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(), "ISO-8859-1"));
            StringBuilder sb = new StringBuilder();
            try {
                String s;
                while ((s = r.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }catch(Exception e)
            {
                Log.e("dni", e.toString());
            }finally {
                r.close();
            }
             a = sb.toString();
        }catch (IOException ex){
            Log.e("dni", ex.toString());
        }
        return a;
    }

}
