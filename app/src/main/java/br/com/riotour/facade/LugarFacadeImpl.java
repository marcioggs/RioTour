package br.com.riotour.facade;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.com.riotour.dao.LugarDAO;
import br.com.riotour.dao.LugarDAOImpl;
import br.com.riotour.dto.LugarDTO;

/**
 * Classe responsável pelas regras de negócio dos lugares.
 */
public class LugarFacadeImpl implements LugarFacade {
	Context ctx;
	LugarDAO dao;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public LugarFacadeImpl(Context ctx) {
		this.ctx = ctx;
		dao = new LugarDAOImpl(ctx);
	}

	@Override
	public Set<LugarDTO> obterLugares() throws IOException {
		Set<LugarDTO> lugares = new HashSet<>();

		lugares.addAll(dao.obterPontosTuristicos());
		lugares.addAll(dao.obterHoteis());
		lugares.addAll(dao.obterMonumentos());
		lugares.addAll(dao.obterMuseus());
		lugares.addAll(dao.obterPraias());

		return lugares;
	}

}
