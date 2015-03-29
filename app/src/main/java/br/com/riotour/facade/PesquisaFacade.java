package br.com.riotour.facade;

import java.util.List;

/**
 * Created by root on 29/03/15.
 */
public interface PesquisaFacade {
	/**
	 * Insere o termo, caso não exista,
	 * @param termo Termo da pesquisa
	 */
	void inserirTermo(String termo);

	/**
	 * Obtém todos os termos de pesquisa.
	 * @return Termos de pesquisa
	 */
	List<String> obterTermos();

	/**
	 * Deleta todos os termos de pesquisa.
	 */
	void deletarTodosTermos();
}
