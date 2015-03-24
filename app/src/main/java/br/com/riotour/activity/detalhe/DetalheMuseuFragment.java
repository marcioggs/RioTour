package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.MuseuDTO;

public class DetalheMuseuFragment extends Fragment {

	public static final String KEY_LUGAR = "lugar";
	private MuseuDTO lugar;

	/**
	 * Obtém a instância do fragmento com os parãmetros configurados.
	 * @param m Museu
	 * @return Fragment
	 */
	public static DetalheMuseuFragment newInstance(MuseuDTO m) {
		DetalheMuseuFragment f = new DetalheMuseuFragment();

		Bundle args = new Bundle();
		args.putSerializable(KEY_LUGAR, m);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lugar = (MuseuDTO) getArguments().getSerializable(KEY_LUGAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detalhe_museu, container, false);

		TextView campoEndereco = (TextView) v.findViewById(R.id.campo_endereco);
		TextView campoNumero = (TextView) v.findViewById(R.id.campo_numero);
		TextView campoBairro = (TextView) v.findViewById(R.id.campo_bairro);
		TextView campoTelefone = (TextView) v.findViewById(R.id.campo_telefone);

		campoEndereco.setText(lugar.getEndereco());
		campoNumero.setText(lugar.getNumero());
		campoBairro.setText(lugar.getBairro());
		campoTelefone.setText(lugar.getTelefone());

		return v;
	}
}
