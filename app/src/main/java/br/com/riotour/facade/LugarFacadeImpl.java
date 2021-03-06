package br.com.riotour.facade;

import android.content.Context;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
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
     *
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

	@Override
	public Set<LugarDTO> filtrarLugares(final Map<String, Boolean> lugaresAtivos, Set<LugarDTO> lugares) {
		return	FluentIterable
					.from(lugares)
					.filter(new Predicate<LugarDTO>() {
						@Override
						public boolean apply(LugarDTO lugar) {
							return lugaresAtivos.get(lugar.getTipo()).booleanValue();
						}
					})
					.toSet();
	}

}
