package com.example.sublimadefashionapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import com.example.sublimadefashionapp.Fragments.CarritoFragment;
import com.example.sublimadefashionapp.Fragments.CatalogoFragment;
import com.example.sublimadefashionapp.Fragments.DeseadosFragment;
import com.example.sublimadefashionapp.Fragments.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements InicioFragment.OnFragmentInteractionListener, CatalogoFragment.OnFragmentInteractionListener,
        CarritoFragment.OnFragmentInteractionListener, DeseadosFragment.OnFragmentInteractionListener{
    RecyclerView rvCatalogo;
    String id;
    List<Producto> lp;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    private TextView txtNombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setContentView(R.layout.header_navigation_drawer);
        setToolbar();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        //Fragment Inicial del BottomNavigation
        id = "iniciofragment";
        InicioFragment fragment = InicioFragment.newInstance("id", id);
        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,fragment).commit();

        //SideBar menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Navigation View
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View header = navigationView.getHeaderView(0);
        txtNombreUsuario = (TextView) header.findViewById(R.id.txtnombreusuario);

        if (firebaseUser != null) {
            txtNombreUsuario.setText(firebaseUser.getDisplayName());
        }
        //Metodo del Navigation View
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.perfilItem:

                        break;
                    case R.id.comprasItem:

                        break;
                    case R.id.pedidosItem:

                        break;
                    case R.id.cerrarsesionItem:
                        if(firebaseUser != null){
                            firebaseAuth.getInstance().signOut();
                        }
                        break;
                }

                return true;
            }
        });
        //Barra de navegacion
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottmnavigation_view);
        //Metodo de la barra de navegacion inferior
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.carritoItem:
                        id = "carritofragment";
                        CarritoFragment carritoFragment = CarritoFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,carritoFragment).commit();
                        break;
                    case R.id.catalogoItem:
                        id = "catalogofragment";
                        CatalogoFragment catalogoFragment = CatalogoFragment.newInstance("id", id);
                       getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,catalogoFragment).commit();
                        break;
                    case R.id.inicioItem:
                        id = "iniciofragment";
                        InicioFragment inicioFragment = InicioFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,inicioFragment).commit();
                        break;
                    case R.id.deseadosItem:
                        id = "deseadosfragment";
                        DeseadosFragment deseadosFragment = DeseadosFragment.newInstance("id", id);
                        getSupportFragmentManager().beginTransaction().replace(R.id.conteiner_bottomnavigation,deseadosFragment).commit();
                        break;
                }

                return true;
            }
        });//Final del metodo de la barra de navegacion inferior
    }

    private void setToolbar(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Metodo para abrir sidebar (menu lateral)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //abrir menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }//Fin del Metodo para abrir sidebar (menu lateral)

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
