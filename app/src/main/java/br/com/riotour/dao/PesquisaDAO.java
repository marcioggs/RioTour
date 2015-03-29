package br.com.riotour.dao;

import java.util.List;

/**
 * Created by root on 29/03/15.
 */
public interface PesquisaDAO {
	/**
	 * Insere termo de pesquisa.
	 * @param termo Termo de pesquisa
	 * @return Verdadeiro caso tenha sido inserido
	 */
	void inserirTermo(String termo);

	/**
	 * Obtém todos os termos de pesquisa.
	 * @return Termos de pesquisa
	 */
	List<String> obterTermos();

	/**
	 * Termo a ser pesquisado.
	 * @param termo Termo
	 * @return Termo encontrado
	 */
	String obterTermo(String termo);

	/**
	 * Atualiza o timestamp do registro com o termo informado.
	 * @param termo Termo
	 */
	void atualizarTimestamp(String termo);

	/**
	 * Deleta todos os termos de pesquisa.
	 */
	void deletarTodosTermos();
}
