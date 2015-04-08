package br.com.riotour.dto;

import com.google.android.gms.maps.model.LatLng;
import com.google.common.base.Strings;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

/**
 * Classe responsável pela modelagem do lugar.
 */
public abstract class LugarDTO implements ClusterItem, Serializable {

    private String nome;
    private double latitude;
    private double longitude;
    private int icone;
    private int miniIcone;
    private String tipo;
    private double indSimilaridadePesquisa;

    @Override
    public LatLng getPosition() {
        return new LatLng(getLatitude(), getLongitude());
    }

    public String getPositionToString() {
        return latitude + "," + longitude;
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

    public void setLatitude(String latitude) {
        if (Strings.isNullOrEmpty(latitude))
            this.latitude = Double.parseDouble("0");
        else
            this.latitude = Double.parseDouble(latitude);
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLongitude(String longitude) {
        if (Strings.isNullOrEmpty(longitude))
            this.longitude = Double.parseDouble("0");
        else
            this.longitude = Double.parseDouble(longitude);
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    public int getMiniIcone() {
        return miniIcone;
    }

    public void setMiniIcone(int miniIcone) {
        this.miniIcone = miniIcone;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getIndSimilaridadePesquisa() {
        return indSimilaridadePesquisa;
    }

    public void setIndSimilaridadePesquisa(double indSimilaridadePesquisa) {
        this.indSimilaridadePesquisa = indSimilaridadePesquisa;
    }
}
