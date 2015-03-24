package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.riotour.R;
import br.com.riotour.dto.MonumentoDTO;

public class DetalheMonumentoFragment extends Fragment {

	public static final String KEY_LUGAR = "lugar";
	private MonumentoDTO lugar;

	/**
	 * Obtém a instância do fragmento com os parãmetros configurados.
	 * @param m Monumento
	 * @return Fragment
	 */
	public static DetalheMonumentoFragment newInstance(MonumentoDTO m) {
		DetalheMonumentoFragment f = new DetalheMonumentoFragment();

		Bundle args = new Bundle();
		args.putSerializable(KEY_LUGAR, m);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lugar = (MonumentoDTO) getArguments().getSerializable(KEY_LUGAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detalhe_monumento, container, false);

		ImageView campoFotoMonumento = (ImageView) v.findViewById(R.id.campo_foto_monumento);
		TextView campoHistorico = (TextView) v.findViewById(R.id.campo_historico);
		TextView campoAutor = (TextView) v.findViewById(R.id.campo_autor);
		TextView campoInauguracao = (TextView) v.findViewById(R.id.campo_inauguracao);
		TextView campoLocalizacao = (TextView) v.findViewById(R.id.campo_localizacao);

		Picasso.with(getActivity()).load(lugar.getUrlFoto()).into(campoFotoMonumento);
		campoHistorico.setText(lugar.getHistorico());
		campoAutor.setText(lugar.getAutor());
		campoInauguracao.setText(lugar.getInauguracao());
		campoLocalizacao.setText(lugar.getLocalizacao());

		return v;
	}
}
