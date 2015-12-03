package com.bikeworld.bikeworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class PantallaLogin extends AppCompatActivity {

    public EditText userName;
    public EditText passwordUser;
    public Button introPasswordUser;
    public Button buttonCreateNewUser;

    //URL: https://dazzling-inferno-4414.firebaseio.com/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName = (EditText) findViewById(R.id.introUserName);
        passwordUser = (EditText) findViewById(R.id.introPasswordUser);

        // Use Firebase to populate the list.
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user");

        getDatos();

    }

    public void getDatos(){
        //accedo a los datos que hay dentro de user/text/
        Firebase myFirebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/");
        myFirebase.child("userLog").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<Object, Map<String, Object>> data = (Map<Object, Map<String, Object>>) dataSnapshot.getValue();
                System.out.println("DATA: "+data);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void onCreateUser(View view) {
        Intent act2 = new Intent(this, createUser.class);
        startActivity(act2);
    }

    public void onLogIn(View view) {
        //ESTO SOLAPA UN USUARIO ANTERIOR--------------------------------------
        //intro usuario
        /*new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user")
                //.push()
                .child("userLog")
                .child("userName")
                //.child("password")
                .setValue(userName.getText().toString());
        System.out.println("USUARIO INTRO: "+userName);
        //intro password
        new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user")
                //.push()
                .child("userLog")
                .child("password")
                .setValue(passwordUser.getText().toString());
        System.out.println("USUARIO INTRO: "+passwordUser);*/
        //FIN SOLAPAR--------------------------------------------------------------------------
        //CREAR NUEVO USUARIO********************************************************
        Firebase firebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user");
        String strUserName = userName.getText().toString();
        String strPassword = passwordUser.getText().toString();
        Firebase userRef = firebase.child("userLog_"+strUserName);
        User user = new User();
        user.setUserName(strUserName);
        user.setPassword(strPassword);
        System.out.println("USUARI: " + user.getUserName());
        System.out.println("PASSWORD: "+user.getPassword());
        userRef.child("userName").setValue(user.getUserName());
        userRef.child("password").setValue(user.getPassword());
        //FIN CREAR NUEVO USER**********************************************************
        //te manda a menú
        Intent act2 = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(act2);
    }

}
