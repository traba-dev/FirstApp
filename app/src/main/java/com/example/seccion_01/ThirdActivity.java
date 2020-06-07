package com.example.seccion_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    public ImageButton phone, browser, camera, contacts, mail, call;
    public EditText edMail, edPhone;
    private final int PHONE_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        phone = (ImageButton) findViewById(R.id.btnImgPhone);
        browser = (ImageButton) findViewById(R.id.btnImgEmail);
        camera = (ImageButton) findViewById(R.id.btnImgCamera);
        contacts = (ImageButton) findViewById(R.id.btnImgContactos);
        mail = (ImageButton) findViewById(R.id.btnImgCorreo);
        call = (ImageButton) findViewById(R.id.btnImgCall);

        edPhone = (EditText) findViewById(R.id.editPhone);
        edMail = (EditText) findViewById(R.id.editEmail);

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumbre = edPhone.getText().toString();
                if (phoneNumbre != null) {
                    //comprobar version actual de android del celular
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CODE);
                    } else {
                        OlderVersions(phoneNumbre);
                    }
                }
            }

            private void OlderVersions(String phoneNumbre) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumbre));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(i);
                } else {
                    Toast.makeText(ThirdActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url =   edMail.getText().toString();
                if (url != null && !url.isEmpty()) {
                    Intent intentWeb = new Intent();
                    intentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));






                    startActivity(intentWeb);
                }
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamera);
            }
        });
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContacto = new Intent();
                intentContacto.setAction(Intent.ACTION_VIEW);
                intentContacto.setData(Uri.parse("content://contacts/people"));
                startActivity(intentContacto);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edMail.getText().toString();
                if (!mail.isEmpty()){
                    Intent intentMail = new Intent();
                    //Correo completo
                    intentMail.setAction(Intent.ACTION_SEND);
                    intentMail.setData(Uri.parse(mail));
                    intentMail.setType("text/plain");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT,"Mail's Title");
                    intentMail.putExtra(Intent.EXTRA_TEXT,"This is a email test");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"ccoronado@innovasof.com", "traba2402@gmail.com"});
                    startActivity(Intent.createChooser(intentMail,"Elige cliente de correo"));
                    //Correo incompletos
//                    intentMail.setAction(Intent.ACTION_SENDTO);
//                    intentMail.setData(Uri.parse("mailto:" + edMail.getText().toString()));
//                    startActivity(intentMail);
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent();
                String number = edPhone.getText().toString();
                if (!number.isEmpty()) {
                    intentCall.setAction(Intent.ACTION_DIAL);
                    intentCall.setData(Uri.parse("tel:"+number));
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this,"Phone Number Required",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CODE:

                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        String phoneNumbre = edPhone.getText().toString();
                        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumbre));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(i);
                    }else {
                        //No dio permiso
                        Toast.makeText(ThirdActivity.this," you declined the access", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean checkPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return  result == PackageManager.PERMISSION_GRANTED;
    }
}
