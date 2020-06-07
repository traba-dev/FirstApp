package com.example.seccion_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    public EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fuerza y carga icono en action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String message = bundle.getString("back");
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        }


        name = (EditText) findViewById(R.id.txtName);
        btn = (Button) findViewById(R.id.button2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myMethod("On click");
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                String nombre = name.getText().toString();
                if (!nombre.isEmpty()) {
                    i.putExtra("saludo",nombre);
                    startActivity(i);
                }
            }
        });
    }
    public void myMethod(String message) {
        String text = message;
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
