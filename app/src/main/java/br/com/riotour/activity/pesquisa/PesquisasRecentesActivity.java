package br.com.riotour.activity.pesquisa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dao.PesquisaDAO;
import br.com.riotour.dto.LugarDTO;


public class PesquisasRecentesActivity extends ActionBarActivity {

    public static final String LUGARES_KEY = "lugares";
    public static final String PESQUISA_KEY = "pesquisa";

    private ArrayAdapter<String> adapter;
    private List<String> pesquisas;
    private PesquisaDAO pesquisaDAO;
    private ListView listViewPesquisas;
    private Set<LugarDTO> lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisas_recentes);
        lugares = (HashSet<LugarDTO>) getIntent().getSerializableExtra(LUGARES_KEY);
        pesquisaDAO = new PesquisaDAO(this);
        pesquisas = pesquisaDAO.queryPesquisa();
        configurarViewPesquisas();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pesquisas_recentes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Configura a view de resultados.
     */
    private void configurarViewPesquisas() {
        listViewPesquisas = (ListView) findViewById(R.id.list_view_pesquisas);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, pesquisas);

        listViewPesquisas.setAdapter(adapter);

        listViewPesquisas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: chama activity de pesquisa passando a pesquisa
                iniciarPesquisa(pesquisas.get(position));
            }
        });
    }

    /**
     * Inicia a activity de pesquisa.
     */
    private void iniciarPesquisa(String pesquisa) {
        Intent intent = new Intent(PesquisasRecentesActivity.this, PesquisaActivity.class);
        intent.putExtra(PesquisasRecentesActivity.PESQUISA_KEY, pesquisa);
        intent.putExtra(PesquisasRecentesActivity.LUGARES_KEY, (HashSet) lugares);
        startActivity(intent);
    }
}
