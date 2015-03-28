package br.com.riotour.activity.pesquisa;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.riotour.R;
import br.com.riotour.activity.detalhe.DetalheActivity;
import br.com.riotour.dao.PesquisaDAO;
import br.com.riotour.dto.LugarDTO;


public class PesquisasRecentesActivity extends ActionBarActivity {

    private PesquisaDAO pesquisaDAO;
    private ListView pesquisas;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisas_recentes);
        pesquisaDAO = new PesquisaDAO(this);
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
        pesquisas = (ListView) findViewById(R.id.list_view_pesquisas);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, pesquisaDAO.queryPesquisa());

        pesquisas.setAdapter(adapter);

        pesquisas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: chama activity de pesquisa passando a pesquisa
            }
        });
    }
}
