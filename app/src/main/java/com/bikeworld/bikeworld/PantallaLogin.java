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
    //chuleta: https://www.firebase.com/docs/android/guide/saving-data.html
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
        //accedo a los datos que hay dentro de user
        Firebase myFirebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/");
        myFirebase.child("user").addValueEventListener(new ValueEventListener() {
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
        String strUserName = userName.getText().toString();
        String strPassword = passwordUser.getText().toString();
        //para hacer los gets
        Firebase referencia = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("DATOS PARA EL LOGIN"+dataSnapshot.getValue());
                String dataLogin = dataSnapshot.getValue().toString();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Intent act2 = new Intent(this, MenuPrincipalUsuario.class);
        startActivity(act2);
    }

}
