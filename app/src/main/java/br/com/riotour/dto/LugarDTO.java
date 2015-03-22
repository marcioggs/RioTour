package br.com.riotour.dto;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Classe responsável pela modelagem do lugar.
 */
public abstract class LugarDTO implements ClusterItem  {

	private String nome;
	private double latitude;
	private double longitude;
	private int icone;
	private String tipo;

	@Override
	public LatLng getPosition() {
		return new LatLng(getLatitude(), getLongitude());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = Double.parseDouble(latitude);
	}

	public void setLongitude(String longitude) {
		this.longitude = Double.parseDouble(longitude);
	}

	public int getIcone() {
		return icone;
	}

	public void setIcone(int icone) {
		this.icone = icone;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
