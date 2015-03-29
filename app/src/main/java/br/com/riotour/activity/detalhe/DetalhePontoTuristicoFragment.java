package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.listener.LigarTelefoneClickListener;

public class DetalhePontoTuristicoFragment extends DetalheFragment {

    public static final String KEY_LUGAR = "lugar";
    private PontoTuristicoDTO lugar;

    /**
     * Obtém a instância do fragmento com os parãmetros configurados.
     *
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
        TextView campoTelefone = (TextView) v.findViewById(R.id.campo_telefone);

        StringBuffer endereco = new StringBuffer();
        endereco.append(lugar.getEndereco());
        if (!lugar.getNumero().isEmpty())
            endereco.append(", " + lugar.getNumero());
        if (!lugar.getBairro().isEmpty())
            endereco.append(" - " + lugar.getBairro());

        campoEndereco.setText(endereco);
        campoTelefone.setText(lugar.getTelefone());
	    campoTelefone.setOnClickListener(new LigarTelefoneClickListener(getActivity()));

	    esconderCamposVazios(v);

        return v;
    }
}
