package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
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

	//TODO: Trocar nomes por ícones? (Ex: Ícone de email)
	//TODO: Pegar imagem do Google imagens quando não tiver?
	//TODO: Colocar botão de voltar na action bar.
	//TODO: Criar intent para permitir telefonar para os números da app.
	//TODO: Criar intent para permitir enviar email para os emails da app.
	//TODO: Colocar imagem estática do mapa apenas com o lugar selecionado no tipo da tela.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);

		LugarDTO lugar = (LugarDTO) getIntent().getSerializableExtra("lugar");

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
    }

	private String obterURLMapaEstatico(LugarDTO lugar) {
		StringBuffer url = new StringBuffer();

		String posicao = lugar.getPositionToString();

		url.append("https://maps.googleapis.com/maps/api/staticmap?center=");
		url.append(posicao);
		url.append("&zoom=15&size=400x150&markers=color:black%7C");
		url.append(posicao);

		return url.toString();
	}

	/**
	 * Obtém o fragmento de detalhe.
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
}
