package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.common.primitives.Floats;

import br.com.riotour.R;
import br.com.riotour.dto.HotelDTO;

public class DetalheHotelFragment extends Fragment {

	public static final String KEY_LUGAR = "lugar";
	private HotelDTO lugar;

	/**
	 * Obtém a instância do fragmento com os parãmetros configurados.
	 * @param h Hotel
	 * @return Fragment
	 */
	public static DetalheHotelFragment newInstance(HotelDTO h) {
		DetalheHotelFragment f = new DetalheHotelFragment();

		Bundle args = new Bundle();
		args.putSerializable(KEY_LUGAR, h);
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lugar = (HotelDTO) getArguments().getSerializable(KEY_LUGAR);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detalhe_hotel, container, false);

		TextView campoLogradouro = (TextView) v.findViewById(R.id.campo_logradouro);
		TextView campoNumero= (TextView) v.findViewById(R.id.campo_numero);
		TextView campoBairro = (TextView) v.findViewById(R.id.campo_bairro);
		TextView campoFax = (TextView) v.findViewById(R.id.campo_fax);
		TextView campoEmail = (TextView) v.findViewById(R.id.campo_email);
		RatingBar campoCategoriaRating = (RatingBar) v.findViewById(R.id.campo_categoria_rating);
		TextView campoCategoriaTexto = (TextView) v.findViewById(R.id.campo_categoria_texto);
		TextView campoQtdAcomodacoesCadeirante = (TextView) v.findViewById(R.id.campo_qtd_acomodacoes_cadeirantes);
		TextView campoQtdAcomodacoesCaoGuia = (TextView) v.findViewById(R.id.campo_qtd_acomodacoes_cao_guia);
		TextView campoTelefoneParaSurdos = (TextView) v.findViewById(R.id.campo_telefone_para_surdos);
		TextView campoTelefone = (TextView) v.findViewById(R.id.campo_telefone);
		TextView campoCnpj = (TextView) v.findViewById(R.id.campo_cnpj);
		TextView campoIdiomasFalados = (TextView) v.findViewById(R.id.campo_idiomas_falados);

		campoLogradouro.setText(lugar.getLogradouro());
		campoNumero.setText(lugar.getNumero());
		campoBairro.setText(lugar.getBairro());
		campoFax.setText(lugar.getFax());
		campoEmail.setText(lugar.getEmail());

		Float categoriaRating = Floats.tryParse(lugar.getCategoria());
		if (categoriaRating != null) {
			campoCategoriaRating.setRating(categoriaRating);
			campoCategoriaRating.setVisibility(View.VISIBLE);
			campoCategoriaTexto.setVisibility(View.GONE);
		} else {
			campoCategoriaTexto.setText(lugar.getCategoria());
			campoCategoriaRating.setVisibility(View.GONE);
			campoCategoriaTexto.setVisibility(View.VISIBLE);
		}

		campoQtdAcomodacoesCadeirante.setText(lugar.getQtdAcomodacoesCadeirante());
		campoQtdAcomodacoesCaoGuia.setText(lugar.getQtdAcomodacoesCaoGuia());
		campoTelefoneParaSurdos.setText(lugar.getTelefoneParaSurdos());
		campoTelefone.setText(lugar.getTelefone());
		campoCnpj.setText(lugar.getCnpj());
		campoIdiomasFalados.setText(lugar.getIdiomasFalados());

		return v;
	}
}
