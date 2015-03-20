package br.com.riotour.dao;

import java.io.IOException;
import java.util.Set;

import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;

/**
 * Interface responsável pela persistência dos lugares.
 */
public interface LugarDAO {

	/**
	 * Obtém os pontos turísticos.
	 * @return Pontos turísticos
	 */
	Set<PontoTuristicoDTO> obterPontosTuristicos() throws IOException;

	/**
	 * Obtém os hotéis.
	 * @return Hotéis.
	 */
	Set<HotelDTO> obterHoteis();

	/**
	 * Obtém os monumentos.
	 * @return Monumentos
	 */
	Set<MonumentoDTO> obterMonumentos();

	/**
	 * Obtém os museus.
	 * @return Museus
	 */
	Set<MuseuDTO> obterMuseus();

	/**
	 * Obtém as praias.
	 * @return Praias
	 */
	Set<PraiaDTO> obterPraias();

}
