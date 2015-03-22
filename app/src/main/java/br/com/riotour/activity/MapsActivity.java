package br.com.riotour.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.facade.LugarFacade;
import br.com.riotour.facade.LugarFacadeImpl;

public class MapsActivity extends ActionBarActivity {

	//TODO: Traduzir para inglês.
	//TODO: Colocar strings em arquivo.

    private GoogleMap mapa;
	private Set<LugarDTO> lugares;
	private ClusterManager<LugarDTO> clusterManager;

	private static final LatLng POS_RJ = new LatLng(-22.9068467, -43.1728965);
	private static final float ZOOM_RJ = 13f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

	    obterLugares();
	    configurarMapa();
    }

	@Override
	protected void onResume() {
		super.onResume();
		configurarMapa();
	}

	/**
	 * Obtém os lugares.
	 */
	private void obterLugares() {
		LugarFacade facade = new LugarFacadeImpl(getApplicationContext());

		try {
			lugares = facade.obterLugares();
		} catch (IOException e) {
			Toast.makeText(MapsActivity.this, "Ocorreu um erro ao ler o arquivo", Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * Configura o mapa.
	 */
    private void configurarMapa() {
        if (mapa == null) {
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

	        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(POS_RJ, ZOOM_RJ));

	        clusterManager = new ClusterManager<>(this, mapa);
	        clusterManager.setRenderer(new LugarRenderer(getApplicationContext(), mapa, clusterManager));
	        mapa.setOnCameraChangeListener(clusterManager);
	        mapa.setOnMarkerClickListener(clusterManager);
	        mapa.setOnInfoWindowClickListener(clusterManager);

	        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<LugarDTO>() {
		        @Override
		        public boolean onClusterClick(Cluster<LugarDTO> lugarCluster) {
			        Toast.makeText(MapsActivity.this, "Use o zoom para detalhar " + lugarCluster.getSize() + " itens.", Toast.LENGTH_SHORT).show();
			        return true;
		        }
	        });

	        clusterManager.setOnClusterItemInfoWindowClickListener(new ClusterManager.OnClusterItemInfoWindowClickListener<LugarDTO>() {
		        @Override
		        public void onClusterItemInfoWindowClick(LugarDTO lugar) {
			        //TODO: Criar e chamar activity que detalha as informações.
			        Toast.makeText(MapsActivity.this, lugar.getNome(), Toast.LENGTH_SHORT).show();
		        }
	        });

	        clusterManager.addItems(lugares);
	        clusterManager.cluster();
        }
    }

}
