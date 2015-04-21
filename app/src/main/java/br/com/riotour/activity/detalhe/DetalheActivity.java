package br.com.riotour.activity.detalhe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import br.com.riotour.R;
import br.com.riotour.activity.common.BaseActivity;
import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;

public class DetalheActivity extends BaseActivity {

    public static final String LUGAR_KEY = "lugar";
    private LugarDTO lugar;
    //TODO: Pegar imagem do Google imagens quando não tiver?

    @Override
    protected String getScreenName() {
        return "Detalhe";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lugar = (LugarDTO) getIntent().getSerializableExtra(LUGAR_KEY);

        TextView nomeLugar = (TextView) findViewById(R.id.nome_lugar);
        ImageView iconeLugar = (ImageView) findViewById(R.id.icone_lugar);
        ImageView mapaLugar = (ImageView) findViewById(R.id.mapa_lugar);


        nomeLugar.setText(lugar.getNome());
        iconeLugar.setImageResource(lugar.getIcone());
        Picasso.with(this)
                .load(obterURLMapaEstatico(lugar))
                .fit()
                .centerCrop()
                .into(mapaLugar);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentDetalhe);

        if (fragment == null) {
            manager.beginTransaction()
                    .add(R.id.fragmentDetalhe, obterFragmentoDetalhe(lugar))
                    .commit();
        }

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Obtém a URL da imagem do mapa estático.
     *
     * @param lugar Lugar
     * @return URL
     */
    private String obterURLMapaEstatico(LugarDTO lugar) {
        StringBuffer url = new StringBuffer();

        String posicao = lugar.getPositionToString();

        url.append("https://maps.googleapis.com/maps/api/staticmap?center=");
        url.append(posicao);
        url.append("&zoom=15&size=400x150&markers=color:black%7C");
        url.append(posicao);
        url.append("&key=AIzaSyCXdbYHVQsMxibxsSknG47K1wQf2paFmRc");

        return url.toString();
    }

    /**
     * Obtém o fragmento de detalhe.
     *
     * @param lugar Lugar com os dados de detalhes
     * @return Fragmento
     */
    private Fragment obterFragmentoDetalhe(LugarDTO lugar) {
        Fragment f = null;

        if (lugar instanceof MonumentoDTO) {
            f = DetalheMonumentoFragment.newInstance((MonumentoDTO) lugar);
        } else if (lugar instanceof HotelDTO) {
            f = DetalheHotelFragment.newInstance((HotelDTO) lugar);
        } else if (lugar instanceof MuseuDTO) {
            f = DetalheMuseuFragment.newInstance((MuseuDTO) lugar);
        } else if (lugar instanceof PontoTuristicoDTO) {
            f = DetalhePontoTuristicoFragment.newInstance((PontoTuristicoDTO) lugar);
        } else if (lugar instanceof PraiaDTO) {
            f = DetalhePraiaFragment.newInstance((PraiaDTO) lugar);
        }

        return f;
    }

    public void abrirCaminho(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + lugar.getPositionToString());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
