package com.bikeworld.bikeworld.Antiguos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bikeworld.bikeworld.ObjetosAntiguos.User;
import com.bikeworld.bikeworld.NuevoMenuPrincipalColumnas;
import com.bikeworld.bikeworld.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class PantallaLogin extends AppCompatActivity {

    public EditText userName;
    public EditText passwordUser;
    public Button introPasswordUser;
    public Button buttonCreateNewUser;

    //crea el objeto para ir acumulando los datos del usuario
    public static User user;

    //URL: https://dazzling-inferno-4414.firebaseio.com/
    //chuleta: https://www.firebase.com/docs/android/guide/saving-data.html
    //chuleta2: http://www.ignacionario.com/2011/03/android-iv-diseno-de-layouts-o.html

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



        //getDatos();

    }

    /*public void getDatos(){
        //accedo a los datos que hay dentro de user
        Firebase myFirebase = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/");
        myFirebase.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<Object, Map<String, Object>> data = (Map<Object, Map<String, Object>>) dataSnapshot.getValue();
                System.out.println("DATA: " + data);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }*/

    public void onCreateUser(View view) {
        Intent act2 = new Intent(this, createUser.class);
        startActivity(act2);
    }

    public void onLogIn(View view) {
        final String strUserName = userName.getText().toString();
        final String strPassword = passwordUser.getText().toString();

        user = new User();
        String obUserName = strUserName;
        user.setUserName(obUserName);
        user.setPassword(strPassword);

        System.out.println("l'usuari es diu: "+strUserName);

        //para hacer los gets
        try {
            final Firebase referencia = new Firebase("https://dazzling-inferno-4414.firebaseio.com/bikeWorld/user/userLog_" + strUserName);
            referencia.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try{
                        System.out.println("DATOS PARA EL LOGIN" + dataSnapshot.getValue());
                        String dataLogin = dataSnapshot.getValue().toString();
                        System.out.println("DATALOGIN: " + dataLogin);
                        //comprueba que el usuario i password coincidan
                        if (dataLogin.contains(strUserName) && dataLogin.contains(strPassword)) {
                            System.out.println("usuari i password correcte");
                            enviarAlMenu();
                        } else {
                            System.out.println("password o usuario incorrectos");
                            Toast.makeText(getApplicationContext(), "Password or User incorrect",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e){
                        System.out.println("password o usuario incorrectos");
                        Toast.makeText(getApplicationContext(), "Password or User incorrect",
                                Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }//Final TRY
        catch (Exception e){
            System.out.println("password o usuario incorrectos");
            Toast.makeText(getApplicationContext(), "Password or User incorrect",
                    Toast.LENGTH_SHORT).show();
        }


    }
    public void enviarAlMenu(){
        Intent act2 = new Intent(this, NuevoMenuPrincipalColumnas.class);
        startActivity(act2);
    }

}
