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

	//TODO: Usar estrelas para exibir categoria do hotel.
	//TODO: Mostrar todas as informações.
	//TODO: Trocar nomes por ícones? (Ex: Ícone de email)
	//TODO: Colocar cada tela em um fragment diferente?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        LugarDTO lugar = (LugarDTO) getIntent().getSerializableExtra("lugar");

        TextView tituloText = (TextView) findViewById(R.id.tituloText);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView detalheText = (TextView) findViewById(R.id.detalheText);

        imageView.setVisibility(View.GONE);

        tituloText.setText(lugar.getNome());

        if (lugar instanceof MonumentoDTO) {

            imageView.setVisibility(View.VISIBLE);
	        MonumentoDTO m = (MonumentoDTO) lugar;

	        Picasso.with(this).load(m.getUrlFoto()).into(imageView);
            detalheText.setText(getString(R.string.history) + ": " + m.getHistorico() + "\n" + "\n" +
                    getString(R.string.author) + ": " + m.getAutor() + "\n" + "\n" +
                    getString(R.string.inaguration) + ": " + m.getInauguracao() + "\n" + "\n" +
                    getString(R.string.localization) + ": " + m.getLocalizacao());
        } else if (lugar instanceof HotelDTO) {

	        HotelDTO h = (HotelDTO) lugar;
	        detalheText.setText(getString(R.string.address) + ": " + h.getLogradouro() + ", " + h.getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ": " + h.getBairro() + "\n" + "\n" +
                    getString(R.string.email) + ": " + h.getEmail() + "\n" + "\n" +
                    getString(R.string.phone) + ": " + h.getTelefone() + "\n" + "\n" +
                    getString(R.string.category) + ": " + h.getCategoria());
        } else if (lugar instanceof MuseuDTO) {

	        MuseuDTO m = (MuseuDTO) lugar;
	        detalheText.setText(getString(R.string.address) + ": " + m.getEndereco() + ", " + m.getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ": " + m.getBairro() + "\n" + "\n" +
                    getString(R.string.phone) + ": " + m.getTelefone());
        } else if (lugar instanceof PontoTuristicoDTO) {

	        PontoTuristicoDTO p = (PontoTuristicoDTO) lugar;
	        detalheText.setText(getString(R.string.address) + ": " + p.getEndereco() + ", " + p.getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ": " + p.getBairro() + "\n" + "\n" +
                    getString(R.string.phone) + ": " + p.getTelefone());
        } else if (lugar instanceof PraiaDTO) {

	        PraiaDTO p = (PraiaDTO) lugar;
	        detalheText.setText(getString(R.string.address) + ": " + p.getEndereco() + ", " + p.getNumero() + "\n" + "\n" +
                    getString(R.string.neighborhood) + ": " + p.getBairro() + "\n" + "\n" +
                            getString(R.string.phone) + ": " + p.getTelefone());
        }
    }
}
