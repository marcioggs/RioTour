package br.com.riotour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.facade.LugarFacade;
import br.com.riotour.facade.LugarFacadeImpl;

public class MapsActivity extends ActionBarActivity {

	//TODO: Traduzir para inglês.
	//TODO: Colocar strings em arquivo.
	//TODO: Trocar ícone do cluster.
	//TODO: Trocar ícone do marcador selecionado.
	//TODO: Manter a posição quando mudar de orientação.

	private GoogleMap mapa;
	private Set<LugarDTO> lugares;
	private ClusterManager<LugarDTO> clusterManager;

	private static final LatLng POS_RJ = new LatLng(-22.9068467, -43.1728965);
	private static final float ZOOM_RJ = 13f;

	//Objetos usados na view de detalhe
	private View detalhe;
	private ImageView icone;
	private TextView tipo;
	private TextView nome;
	private ImageView botaoInfo;
    private LugarDTO lugarSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		configurarViewDetalhe();
		obterLugares();
		configurarMapa();
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
		botaoInfo = (ImageView) findViewById(R.id.botao_info);

		botaoInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, DetalheActivity.class);
                intent.putExtra("lugar", lugarSelecionado);
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

}
