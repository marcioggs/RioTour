package br.com.riotour.activity;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import br.com.riotour.dto.LugarDTO;

public class LugarRenderer extends DefaultClusterRenderer<LugarDTO> {

	public LugarRenderer(Context context, GoogleMap map, ClusterManager<LugarDTO> clusterManager) {
		super(context, map, clusterManager);
	}

	@Override
	protected void onBeforeClusterItemRendered(LugarDTO lugar,
	                                           MarkerOptions markerOptions) {
		markerOptions
				.icon(BitmapDescriptorFactory.fromResource(lugar.getIcone()))
				.title(lugar.getNome());
	}

}