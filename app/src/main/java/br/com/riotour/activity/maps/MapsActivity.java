package br.com.riotour.activity.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.activity.detalhe.DetalheActivity;
import br.com.riotour.activity.pesquisa.PesquisaActivity;
import br.com.riotour.activity.pesquisa.PesquisasRecentesActivity;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.facade.LugarFacade;
import br.com.riotour.facade.LugarFacadeImpl;

public class MapsActivity extends ActionBarActivity {

    //TODO: Trocar ícone do cluster.
    //TODO: Trocar ícone do marcador selecionado.
    //TODO: Manter a posição quando mudar de orientação.
    //TODO: Atualizar materialdrawer foi utilizada uma versão anterior que o botao sanduiche funcionava
    //TODO: Desenvolver Activity de Sobre
    //TODO: Adicionar rota do google maps nos detalhes do local.

    private static final LatLng POS_RJ = new LatLng(-22.9068467, -43.1728965);
    private static final float ZOOM_RJ = 13f;
    private GoogleMap mapa;
    private Set<LugarDTO> lugares;
    private ClusterManager<LugarDTO> clusterManager;
    //Objetos usados na view de detalhe
    private View detalhe;
    private ImageView icone;
    private TextView tipo;
    private TextView nome;
    private ImageView botaoInfo;
    private LugarDTO lugarSelecionado;
    private ImageButton getCurrentLocation;
    private GPSTracker gps;
    private Drawer.Result result = null;
    private boolean filter_hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        configurarDrawer();
        configurarViewDetalhe();
        obterLugares();
        configurarMapa();
        configurarObtemPosicao();
    }

    private void configurarDrawer() {


        result = new Drawer()
                .withActivity(this)
                .withActionBarDrawerToggleAnimated(true)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home"),
                        new SecondaryDrawerItem().withName("Pesquisar"),
                        new SecondaryDrawerItem().withName("Pesquisas Recentes"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Sobre")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (position == 1) {
                            iniciarPesquisa();
                        }
                        if (position == 2) {
                            iniciarPesquisasAnteriores();
                        }
                    }
                })
                .build();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Inicia a activity de pesquisa.
     */
    private void iniciarPesquisa() {
        Intent intent = new Intent(MapsActivity.this, PesquisaActivity.class);
        intent.putExtra(PesquisaActivity.LUGARES_KEY, (HashSet) lugares);
        startActivity(intent);
    }

    /**
     * Inicia a activity de pesquisas anteriores.
     */
    private void iniciarPesquisasAnteriores() {
        Intent intent = new Intent(MapsActivity.this, PesquisasRecentesActivity.class);
        intent.putExtra(PesquisasRecentesActivity.LUGARES_KEY, (HashSet) lugares);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (result.isDrawerOpen())
                    result.closeDrawer();
                else
                    result.openDrawer();
                return true;
            case R.id.menu_search:
                iniciarPesquisa();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    private void configurarObtemPosicao() {
        getCurrentLocation = (ImageButton) findViewById(R.id.getCurrentLocation);
        getCurrentLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MapsActivity.this);

                // Verifica se o GPS esta habilitado
                if (gps.canGetLocation()) {

                    //Toast.makeText(getApplicationContext(), "Sua localização é - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gps.getLatitude(), gps.getLongitude()), mapa.getCameraPosition().zoom + 2));
                    gps.stopUsingGPS();
                } else {
                    // Não conseguiu pegar a localização
                    // GPS or Network não esta habilitada
                    // Pedir para usuário habulitar GPS/Network nas configurações
                    gps.showSettingsAlert();
                }


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarMapa();
    }

    /**
     * Configura a view de detalhe.
     */
    private void configurarViewDetalhe() {
        detalhe = findViewById(R.id.detalhe);
        icone = (ImageView) findViewById(R.id.icone_lugar);
        tipo = (TextView) findViewById(R.id.tipo_lugar);
        nome = (TextView) findViewById(R.id.nome_lugar);

        detalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, DetalheActivity.class);
                intent.putExtra(DetalheActivity.LUGAR_KEY, lugarSelecionado);
                startActivity(intent);
            }
        });
    }

    /**
     * Obtém os lugares.
     */
    private void obterLugares() {
        LugarFacade facade = new LugarFacadeImpl(getApplicationContext());

        try {
            lugares = facade.obterLugares();
        } catch (IOException e) {
            Toast.makeText(MapsActivity.this, getString(R.string.error_reading_file), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Configura o mapa.
     */
    private void configurarMapa() {
        if (mapa == null) {
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(POS_RJ, ZOOM_RJ));

            clusterManager = new ClusterManager<>(this, mapa);
            clusterManager.setRenderer(new LugarRenderer(getApplicationContext(), mapa, clusterManager));
            mapa.setOnCameraChangeListener(clusterManager);
            mapa.setOnMarkerClickListener(clusterManager);
            mapa.setOnInfoWindowClickListener(clusterManager);
            mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    detalhe.setVisibility(View.GONE);
                }
            });

            clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<LugarDTO>() {
                @Override
                public boolean onClusterClick(Cluster<LugarDTO> lugarCluster) {
                    mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(lugarCluster.getPosition(), mapa.getCameraPosition().zoom + 2));
                    return true;
                }
            });

            clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<LugarDTO>() {
                @Override
                public boolean onClusterItemClick(LugarDTO lugar) {
                    mapa.animateCamera(CameraUpdateFactory.newLatLng(lugar.getPosition()));

                    detalhe.setVisibility(View.VISIBLE);
                    icone.setImageResource(lugar.getIcone());
                    tipo.setText(lugar.getTipo());
                    nome.setText(lugar.getNome());
                    lugarSelecionado = lugar;
                    return true;
                }
            });

            clusterManager.addItems(lugares);
            clusterManager.cluster();
        }
    }


    public void filtrar(View view) {
        FloatingActionButton button = (FloatingActionButton) view;
        if (R.id.filter_hotel == view.getId()) {
            if (!filter_hotel) {
                button.setActivated(true);
                button.refreshDrawableState();
                filter_hotel = true;
            } else {
                button.setActivated(false);
                filter_hotel = false;
            }

        }

    }
}
