package br.com.riotour.activity.pesquisa;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dto.LugarDTO;
import br.com.riotour.util.levenshteindistance.LevenshteinDistance;

public class PesquisaActivity extends ActionBarActivity {

	public static final String LUGARES_KEY = "lugares";
	private Set<LugarDTO> lugares;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pesquisa);

		lugares = (HashSet<LugarDTO>) getIntent().getSerializableExtra(LUGARES_KEY);
		configurarViewPesquisa();
	}

	/**
	 * Configura a view de pesquisa.
	 */
	private void configurarViewPesquisa() {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) findViewById(R.id.campo_pesquisa);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		String query = intent.getStringExtra(SearchManager.QUERY);
		pesquisar(query);
	}

	/**
	 * Realzia a pesquisa pela query informada.
	 * @param query Query
	 */
	private void pesquisar(final String query) {
		LugarDTO[] lugaresFiltrados = filtrarResultados(query);
		ListView resultados = (ListView) findViewById(R.id.campo_resultado_pesquisa);
		resultados.setAdapter(new ResultadoPesquisaAdapter(this, lugaresFiltrados));
	}

	/**
	 * Filtra e ordena os resultados a serem exibidos.
	 * @param query Query
	 * @return Resultado
	 */
	private LugarDTO[] filtrarResultados(final String query) {
		Set<LugarDTO> resultado =
			FluentIterable
				.from(lugares)
				.filter(new Predicate<LugarDTO>() {
					@Override
					public boolean apply(LugarDTO lugar) {
						double indSimilaridadePesquisa = LevenshteinDistance.similarity(lugar.getNome(), query);
						lugar.setIndSimilaridadePesquisa(indSimilaridadePesquisa);
						return indSimilaridadePesquisa > 0.30;
					}
				})
				.toSortedSet(new Comparator<LugarDTO>() {
					@Override
					public int compare(LugarDTO l, LugarDTO r) {
						return Double.compare(r.getIndSimilaridadePesquisa(), l.getIndSimilaridadePesquisa());
					}
				});


		return resultado.toArray(new LugarDTO[resultado.size()]);
	}

}