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
import br.com.riotour.activity.common.BaseActivity;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.facade.PesquisaFacade;
import br.com.riotour.facade.PesquisaFacadeImpl;


public class PesquisasRecentesActivity extends BaseActivity {

    public static final String LUGARES_KEY = "lugares";
    public static final String PESQUISA_KEY = "pesquisa";

    private ArrayAdapter<String> adapter;
    private List<String> pesquisas;
    private PesquisaFacade facade;
    private ListView listViewPesquisas;
    private Set<LugarDTO> lugares;

    @Override
    protected String getScreenName() {
        return "Pesquisas Recentes";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisas_recentes);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lugares = (HashSet<LugarDTO>) getIntent().getSerializableExtra(LUGARES_KEY);
        facade = new PesquisaFacadeImpl(this);
        pesquisas = facade.obterTermos();
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
	    switch (item.getItemId()) {
		    case android.R.id.home:
			    finish();
			    return true;
		    case R.id.menu_clear:
			    limparPesquisasRecentes();
			    return true;
		    default:
			    return super.onOptionsItemSelected(item);
	    }
    }

    /**
     * Limpa as pesquisas recentes
     */
    private void limparPesquisasRecentes() {
        facade.deletarTodosTermos();
        pesquisas = facade.obterTermos();
        adapter.clear();
        adapter.notifyDataSetChanged();
        listViewPesquisas.refreshDrawableState();

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
