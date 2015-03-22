package br.com.riotour.dto;

/**
 * Classe responsável pela modelagem do lugar.
 */
public abstract class LugarDTO {

	private String nome;
	private double latitude;
	private double longitude;
	private int icone;

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
}
