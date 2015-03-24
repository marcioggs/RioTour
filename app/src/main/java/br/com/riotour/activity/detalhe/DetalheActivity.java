package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;

public class DetalheActivity extends ActionBarActivity {

	//TODO: Usar estrelas para exibir categoria do hotel.
	//TODO: Trocar nomes por ícones? (Ex: Ícone de email)
	//TODO: Pegar imagem do Google imagens quando não tiver?
	//TODO: Permitir abrir detalhe activity clicando em qualquer lugar do painel de detalhe.
	//TODO: Colocar botão de voltar na action bar.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe);

		LugarDTO lugar = (LugarDTO) getIntent().getSerializableExtra("lugar");

		TextView tituloText = (TextView) findViewById(R.id.tituloText);
		ImageView icone = (ImageView) findViewById(R.id.icone_lugar);

		tituloText.setText(lugar.getNome());
		icone.setImageResource(lugar.getIcone());

		FragmentManager manager = getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragmentDetalhe);

		if (fragment == null) {
			manager.beginTransaction()
					.add(R.id.fragmentDetalhe, obterFragmentoDetalhe(lugar))
					.commit();
	    }
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
