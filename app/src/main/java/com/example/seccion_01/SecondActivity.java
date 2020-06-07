package com.example.seccion_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String saludos = bundle.getString("saludo");
        Toast.makeText(SecondActivity.this,saludos,Toast.LENGTH_SHORT).show();


        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdBack();
            }
});

        }

public void mdBack() {
        Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
        //i.putExtra("back","Hola Volvi");
        startActivity(i);
        }
}
