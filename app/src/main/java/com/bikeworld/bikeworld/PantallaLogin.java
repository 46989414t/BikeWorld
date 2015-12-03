package com.bikeworld.bikeworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

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

        //String strUserName = introUserName.get;

        User user = new User();

        user.setUserName(userName.getText().toString());
        user.setPassword(passwordUser.getText().toString());

        // Use Firebase to populate the list.
        Firebase.setAndroidContext(this);

        System.out.println("RESULNAME: " + user.resulName);
        System.out.println("RESULPASSWORD: "+user.resulPassword);
        System.out.println("RESULNAME2: " + user.getResulName());
        /*Firebase cadenaUser = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/text/");
        cadenaUser.child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<Object, Map<String, Object>> data =
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        /*new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/text")
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //adapter.add((String) dataSnapshot.child("text").getValue());
                        System.out.println("entra en new Firebase");
                        dataSnapshot.child("userName").getValue();
                        System.out.println("USERNAME: " + dataSnapshot.child("userName").getValue());
                        dataSnapshot.child("password").getValue();
                        System.out.println("PASSWORD: "+dataSnapshot.child("password").getValue());
                        //userName.addTextChangedListener((String) dataSnapshot.child("userName"));
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        // adapter.remove((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });*/




        //userName.add((String))




    }

    public void onCreateUser(View view) {
        Intent act2 = new Intent(this, createUser.class);
        startActivity(act2);
    }

    public void onLogIn(View view) {
        Intent act2 = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(act2);
    }
}
