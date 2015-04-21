package br.com.riotour.activity.detalhe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.PraiaDTO;
import br.com.riotour.listener.LigarTelefoneClickListener;

public class DetalhePraiaFragment extends DetalheFragment {

    public static final String KEY_LUGAR = "lugar";
    private PraiaDTO lugar;

    /**
     * Obtém a instância do fragmento com os parãmetros configurados.
     *
     * @param p Praia
     * @return Fragment
     */
    public static DetalhePraiaFragment newInstance(PraiaDTO p) {
        DetalhePraiaFragment f = new DetalhePraiaFragment();

        Bundle args = new Bundle();
        args.putSerializable(KEY_LUGAR, p);
        f.setArguments(args);

        return f;
    }

    @Override
    protected String getScreenName() {
        return "Detalhe - Praia=";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lugar = (PraiaDTO) getArguments().getSerializable(KEY_LUGAR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detalhe_praia, container, false);

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
