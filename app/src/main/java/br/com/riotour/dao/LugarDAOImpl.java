package br.com.riotour.dao;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;

/**
 * Classe responsável pela persistência dos lugares em arquivo CSV.
 */
public class LugarDAOImpl implements LugarDAO {

	Context ctx;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public LugarDAOImpl(Context ctx) {
		this.ctx = ctx;
	}

	//TODO: Obter lugares a partir do CSV

	@Override
	public Set<PontoTuristicoDTO> obterPontosTuristicos() throws IOException {

		Set<PontoTuristicoDTO> lugares = new HashSet<>();

/*		InputStream arquivo = ctx.getResources().openRawResource(R.raw.pontos_turisticos);
		CsvParser.Keyed keyed = new CsvParser().on(arquivo).keyed();

		CsvParser.Keyed.Line linha = keyed.next();

		while (linha != null) {

			PontoTuristicoDTO p = new PontoTuristicoDTO();

			p.setNome(linha.get("Nome"));
			p.setEndereco(linha.get("Endereço"));
			p.setNumero(linha.get("Número"));
			p.setBairro(linha.get("Bairro"));
			p.setTelefone(linha.get("Telefone"));
			p.setLatitude(linha.get("latitude"));
			p.setLongitude(linha.get("longitude"));

			linha = keyed.next();
		}*/

		PontoTuristicoDTO ponto;

		ponto = new PontoTuristicoDTO();
		ponto.setNome("Lugar 1");
		ponto.setLatitude(-22.906847);
		ponto.setLongitude(-43.172896);
		lugares.add(ponto);

		ponto = new PontoTuristicoDTO();
		ponto.setNome("Lugar 2");
		ponto.setLatitude(-22.906202);
		ponto.setLongitude(-43.170122);
		lugares.add(ponto);

		ponto = new PontoTuristicoDTO();
		ponto.setNome("Lugar 3");
		ponto.setLatitude(-22.909404);
		ponto.setLongitude(-43.170701);
		lugares.add(ponto);

		return lugares;
	}

	@Override
	public Set<HotelDTO> obterHoteis() {
		Set<HotelDTO> lugares = new HashSet<>();
		return lugares;
	}

	@Override
	public Set<MonumentoDTO> obterMonumentos() {
		Set<MonumentoDTO> lugares = new HashSet<>();
		return lugares;
	}

	@Override
	public Set<MuseuDTO> obterMuseus() {
		Set<MuseuDTO> lugares = new HashSet<>();
		return lugares;
	}

	@Override
	public Set<PraiaDTO> obterPraias() {
		Set<PraiaDTO> lugares = new HashSet<>();
		return lugares;
	}

}
