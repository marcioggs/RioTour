package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.PontoTuristicoDTO;

public class DetalhePontoTuristicoFragment extends Fragment {

	public static final String KEY_LUGAR = "lugar";
	private PontoTuristicoDTO lugar;

	/**
	 * Obtém a instância do fragmento com os parãmetros configurados.
	 * @param p Ponto turístico
	 * @return Fragment
	 */
	public static DetalhePontoTuristicoFragment newInstance(PontoTuristicoDTO p) {
		DetalhePontoTuristicoFragment f = new DetalhePontoTuristicoFragment();

		Bundle args = new Bundle();
		args.putSerializable(KEY_LUGAR, p);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lugar = (PontoTuristicoDTO) getArguments().getSerializable(KEY_LUGAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detalhe_ponto_turistico, container, false);

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
