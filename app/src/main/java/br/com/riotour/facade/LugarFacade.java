package br.com.riotour.facade;

import java.io.IOException;
import java.util.Set;

import br.com.riotour.dto.LugarDTO;

/**
 * Interface responsável pelas regras de negócio dos lugares.
 */
public interface LugarFacade {

    /**
     * Obtém os lugares que serão exibidos no mapa.
     *
     * @return Lista de lugares
     */
    Set<LugarDTO> obterLugares() throws IOException;

}
