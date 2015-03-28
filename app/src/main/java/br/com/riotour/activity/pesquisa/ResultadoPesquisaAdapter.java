package br.com.riotour.activity.pesquisa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.riotour.R;
import br.com.riotour.dto.LugarDTO;

public class ResultadoPesquisaAdapter extends ArrayAdapter<LugarDTO> {

    private static final int layoutResourceId = R.layout.listview_pesquisa_item;
    private final Context context;
    private final LugarDTO[] lugares;

    /**
     * Construtor.
     *
     * @param context Contexto
     * @param lugares Lugares
     */
    public ResultadoPesquisaAdapter(Context context, LugarDTO[] lugares) {
        super(context, layoutResourceId, lugares);
        this.context = context;
        this.lugares = lugares;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View linha = convertView;
        LugarHolder holder;

        if (linha == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            linha = inflater.inflate(layoutResourceId, parent, false);

            holder = new LugarHolder();
            holder.icone = (ImageView) linha.findViewById(R.id.icone_lugar);
            holder.nome = (TextView) linha.findViewById(R.id.nome_lugar);

            linha.setTag(holder);
        } else {
            holder = (LugarHolder) linha.getTag();
        }

        LugarDTO lugar = lugares[position];
        holder.icone.setImageResource(lugar.getIcone());
        holder.nome.setText(lugar.getNome());

        return linha;
    }

    static class LugarHolder {
        ImageView icone;
        TextView nome;
    }
}
