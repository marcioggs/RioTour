package br.com.riotour.facade;

import android.content.Context;

import java.util.List;

import br.com.riotour.dao.PesquisaDAO;
import br.com.riotour.dao.PesquisaDAOImpl;

public class PesquisaFacadeImpl implements PesquisaFacade {

	private Context ctx;
	PesquisaDAO dao;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public PesquisaFacadeImpl(Context ctx) {
		this.ctx = ctx;
		this.dao = new PesquisaDAOImpl(ctx);
	}

	@Override
	public void inserirTermo(String termo) {
		if (dao.obterTermo(termo) == null) {
			dao.inserirTermo(termo);
		} else {
			dao.atualizarTimestamp(termo);
		}
	}

	@Override
	public List<String> obterTermos() {
		return dao.obterTermos();
	}

	@Override
	public void deletarTodosTermos() {
		dao.deletarTodosTermos();
	}
}
