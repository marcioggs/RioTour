package br.com.riotour.facade;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import br.com.riotour.dto.LugarDTO;

/**
 * Interface responsável pelas regras de negócio dos lugares.
 */
public interface LugarFacade {

    /**
     * Obtém os lugares que serão exibidos no mapa.
     * @return Lista de lugares
     */
    Set<LugarDTO> obterLugares() throws IOException;

	/**
	 * Filtra os lugares.
	 * @param lugaresAtivos Mapa com tipo dos lugares a serem exibidos.
	 * @param lugares Lugares
	 * @return Lugares filtrados
	 */
	Set<LugarDTO> filtrarLugares(Map<String, Boolean> lugaresAtivos, Set<LugarDTO> lugares);

}
