package br.com.riotour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.riotour.R;
import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;

public class DetalheActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        Intent intent = getIntent();
        LugarDTO lugar = (LugarDTO) intent.getSerializableExtra("lugar");

        TextView tituloText = (TextView) findViewById(R.id.tituloText);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView detalheText = (TextView) findViewById(R.id.detalheText);
        imageView.setVisibility(View.GONE);
        tituloText.setText(lugar.getNome());
        if (lugar instanceof MonumentoDTO) {
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(((MonumentoDTO) lugar).getUrlFoto()).into(imageView);
            detalheText.setText(getString(R.string.history) + ": " + ((MonumentoDTO) lugar).getHistorico() + "\n" + "\n" +
                    getString(R.string.author) + ":" + ((MonumentoDTO) lugar).getAutor() + "\n" + "\n" +
                    getString(R.string.inaguration) + ":" + ((MonumentoDTO) lugar).getInauguracao() + "\n" + "\n" +
                            getString(R.string.localization) + ":" + ((MonumentoDTO) lugar).getLocalizacao());
        } else if (lugar instanceof HotelDTO) {
            detalheText.setText(getString(R.string.address) + ":" + ((HotelDTO) lugar).getLogradouro() + ", " + ((HotelDTO) lugar).getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ":" + ((HotelDTO) lugar).getBairro() + "\n" + "\n" +
                    getString(R.string.email) + ":" + ((HotelDTO) lugar).getEmail() + "\n" + "\n" +
                    getString(R.string.phone) + ":" + ((HotelDTO) lugar).getTelefone() + "\n" + "\n" +
                    getString(R.string.category) + ":" + ((HotelDTO) lugar).getCategoria());
        } else if (lugar instanceof MuseuDTO) {
            detalheText.setText(getString(R.string.address) + ":" + ((MuseuDTO) lugar).getEndereco() + ", " + ((MuseuDTO) lugar).getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ":" + ((MuseuDTO) lugar).getBairro() + "\n" + "\n" +
                    getString(R.string.phone) + ":" + ((MuseuDTO) lugar).getTelefone());
        } else if (lugar instanceof PontoTuristicoDTO) {
            detalheText.setText(getString(R.string.address) + ":" + ((PontoTuristicoDTO) lugar).getEndereco() + ", " + ((PontoTuristicoDTO) lugar).getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ":" + ((PontoTuristicoDTO) lugar).getBairro() + "\n" + "\n" +
                    getString(R.string.phone) + ":" + ((PontoTuristicoDTO) lugar).getTelefone());
        } else if (lugar instanceof PraiaDTO) {
            detalheText.setText(getString(R.string.address) + ":" + ((PraiaDTO) lugar).getEndereco() + ", " + ((PraiaDTO) lugar).getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ":" + ((PraiaDTO) lugar).getBairro() + "\n" + "\n" +
                            getString(R.string.phone) + ":" + ((PraiaDTO) lugar).getTelefone());
        }
    }
}
