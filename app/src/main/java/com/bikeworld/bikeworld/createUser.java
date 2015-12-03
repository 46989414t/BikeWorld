package com.bikeworld.bikeworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class createUser extends AppCompatActivity {

    public EditText newUser;
    public EditText password1;
    public EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);

        newUser = (EditText) findViewById(R.id.idNewUser);
        password1 = (EditText) findViewById(R.id.idPassword1);
        password2 = (EditText) findViewById(R.id.idPassword2);

    }

    public void onCreateNewUser(View view) {
        //CREAR NUEVO USUARIO********************************************************
        Firebase firebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user");
        String strUserName = newUser.getText().toString();
        String strPassword = password1.getText().toString();
        String srtPassword2 = password2.getText().toString();
        if(strPassword.equals(srtPassword2)){
            System.out.println("passwords comparados son iguales");
            //crea el nuevo usuario
            Firebase userRef = firebase.child("userLog_"+strUserName);
            User user = new User();
            user.setUserName(strUserName);
            user.setPassword(strPassword);
            System.out.println("USUARI: " + user.getUserName());
            System.out.println("PASSWORD: "+user.getPassword());
            userRef.child("userName").setValue(user.getUserName());
            userRef.child("password").setValue(user.getPassword());
            //te manda a LogIn
            Intent act2 = new Intent(this, PantallaLogin.class);
            startActivity(act2);
        }
        else{
            System.out.println("passwords NO coinciden");
            Intent act2 = new Intent(this, createUser.class);
            startActivity(act2);
        }



    }
}
