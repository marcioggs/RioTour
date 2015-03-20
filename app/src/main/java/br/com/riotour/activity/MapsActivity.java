package br.com.riotour.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.facade.LugarFacade;
import br.com.riotour.facade.LugarFacadeImpl;

public class MapsActivity extends ActionBarActivity {

	//TODO: Traduzir para inglês.

    private GoogleMap mapa;
    private HashMap<Marker, LugarDTO> mapLugares;
	private LugarFacade facade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

	    configurarMapa();
	    facade = new LugarFacadeImpl(getApplicationContext());

	    Set<LugarDTO> lugares = null;
	    try {
		    lugares = facade.obterLugares();
	    } catch (IOException e) {
		    Toast.makeText(MapsActivity.this, "Ocorreu um erro ao ler o arquivo", Toast.LENGTH_SHORT)
				    .show();
	    }
	    exibirMarcadores(lugares);

        mapa.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
	        @Override
	        public void onInfoWindowClick(Marker marker) {

		        LugarDTO lugar = mapLugares.get(marker);
		        Toast.makeText(MapsActivity.this, lugar.getNome(), Toast.LENGTH_SHORT)
				        .show();
	        }
        });
    }

	@Override
    protected void onResume() {
        super.onResume();
        configurarMapa();
    }

	/**
	 * Obtém o framgmento de mapa, caso ainda não tenha sido obtido.
	 */
    private void configurarMapa() {
        if (mapa == null) {
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
        }
    }

	/**
	 * Exibe no mapa os marcadores dos lugares informados.
	 * @param lugares Lugares
	 */
    private void exibirMarcadores(Set<LugarDTO> lugares) {
        mapLugares = new HashMap<Marker, LugarDTO>();

        for (LugarDTO lugar : lugares)  {
            MarkerOptions markerOption = new MarkerOptions()
               .position(new LatLng(lugar.getLatitude(), lugar.getLongitude()))
		       .title(lugar.getNome());

            Marker marcador = mapa.addMarker(markerOption);
	        //TODO: Colocar icone de acordo com ponto turístico
            mapLugares.put(marcador, lugar);
        }
    }

}
