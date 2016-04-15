package com.bikeworld.bikeworld.Antiguos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bikeworld.bikeworld.ObjetosAntiguos.UserProfile;
import com.bikeworld.bikeworld.R;

import java.util.ArrayList;

public class MenuPrincipalUsuario extends AppCompatActivity {

    private ListView lista;
    public ArrayAdapter adapterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DisenoOpcionesDeMenu rutas = new DisenoOpcionesDeMenu(R.drawable.moto1, "Menú Rutas", "Definic Rutas");
        DisenoOpcionesDeMenu concentraciones = new DisenoOpcionesDeMenu(R.drawable.moto2, "Menú Concentraciones", "Definic Concentraciones");
        DisenoOpcionesDeMenu reportajes = new DisenoOpcionesDeMenu(R.drawable.moto1, "Menú Reportajes", "Definic Reportajes");

        ArrayList<DisenoOpcionesDeMenu> datos = new ArrayList<DisenoOpcionesDeMenu>();
        datos.add(rutas);
        datos.add(concentraciones);
        datos.add(reportajes);

        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new Lista_adaptador(this, R.layout.estilo_seleccion, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((DisenoOpcionesDeMenu) entrada).get_textoEncima());

                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                    if (texto_inferior_entrada != null)
                        texto_inferior_entrada.setText(((DisenoOpcionesDeMenu) entrada).get_textoDebajo());

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageResource(((DisenoOpcionesDeMenu) entrada).get_idImagen());
                }
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                DisenoOpcionesDeMenu elegido = (DisenoOpcionesDeMenu) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
                Toast toast = Toast.makeText(MenuPrincipalUsuario.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }


    public void onEnviarAlMenuAsignado(View view) {
    }

    public void onGoToPerfil(View view) {
        Intent act2 = new Intent(this, UserProfile.class);
        startActivity(act2);
    }
}
