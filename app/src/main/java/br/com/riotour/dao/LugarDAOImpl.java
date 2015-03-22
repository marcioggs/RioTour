package br.com.riotour.dao;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import br.com.riotour.R;
import br.com.riotour.dto.HotelDTO;
import br.com.riotour.dto.MonumentoDTO;
import br.com.riotour.dto.MuseuDTO;
import br.com.riotour.dto.PontoTuristicoDTO;
import br.com.riotour.dto.PraiaDTO;
import br.com.riotour.util.csv.CsvReader;

/**
 * Classe responsável pela persistência dos lugares em arquivo CSV.
 */
public class LugarDAOImpl implements LugarDAO {

	//TODO: Acertar encoding. Os acentos estão ficando errados.
	//TODO: Nos arquivos CSV originais, alguns pontos estão sem lat e long, tratar para desconsiderar na aplicação caso baixe os arquivos em tempo de execução.

	Context ctx;

	/**
	 * Construtor.
	 * @param ctx Contexto
	 */
	public LugarDAOImpl(Context ctx) {
		this.ctx = ctx;
	}

	@Override
	public Set<PontoTuristicoDTO> obterPontosTuristicos() throws IOException {
		Set<PontoTuristicoDTO> lugares = new HashSet<>();

		InputStream arquivo = ctx.getResources().openRawResource(R.raw.pontos_turisticos);
		CsvReader.Keyed keyed = new CsvReader(arquivo).keyed();

		CsvReader.Keyed.Line linha = keyed.next();

		while (linha != null) {
			PontoTuristicoDTO lugar = new PontoTuristicoDTO();

			lugar.setIcone(R.drawable.ponto_turistico);
			lugar.setNome(linha.get("Nome"));
			lugar.setEndereco(linha.get("Endereço"));
			lugar.setNumero(linha.get("Número"));
			lugar.setBairro(linha.get("Bairro"));
			lugar.setTelefone(linha.get("Telefone"));
			lugar.setLatitude(linha.get("latitude"));
			lugar.setLongitude(linha.get("longitude"));

			lugares.add(lugar);
			linha = keyed.next();
		}

		return lugares;
	}

	@Override
	public Set<HotelDTO> obterHoteis() throws IOException {
		Set<HotelDTO> lugares = new HashSet<>();

		InputStream arquivo = ctx.getResources().openRawResource(R.raw.hoteis);
		CsvReader.Keyed keyed = new CsvReader(arquivo).keyed();

		CsvReader.Keyed.Line linha = keyed.next();

		while (linha != null) {
			HotelDTO lugar = new HotelDTO();

			lugar.setIcone(R.drawable.hotel);
			lugar.setNome(linha.get("Nome"));
			lugar.setLogradouro(linha.get("Logradouro"));
			lugar.setNumero(linha.get("Número"));
			lugar.setBairro(linha.get("Bairro"));
			lugar.setTelefone(linha.get("Telefone"));
			lugar.setFax(linha.get("Fax"));
			lugar.setCategoria(linha.get("Categoria"));
			lugar.setEmail(linha.get("Email"));
			lugar.setLatitude(linha.get("Latitude"));
			lugar.setLongitude(linha.get("Longitude"));
			lugar.setQtdAcomodacoesCadeirante(linha.get("Acomodação de Cadeirante"));
			lugar.setQtdAcomodacoesCaoGuia(linha.get("Acomodação de Cão Guia"));
			lugar.setTelefoneParaSurdos(linha.get("Telefone para Surdos"));
			lugar.setCnpj(linha.get("CNPJ"));
			lugar.setIdiomasFalados(linha.get("Idiomas Falados"));

			lugares.add(lugar);
			linha = keyed.next();
		}

		return lugares;
	}

	@Override
	public Set<MonumentoDTO> obterMonumentos() throws IOException {
		Set<MonumentoDTO> lugares = new HashSet<>();

		InputStream arquivo = ctx.getResources().openRawResource(R.raw.monumentos);
		CsvReader.Keyed keyed = new CsvReader(arquivo).keyed();

		CsvReader.Keyed.Line linha = keyed.next();

		while (linha != null) {
			MonumentoDTO lugar = new MonumentoDTO();

			lugar.setIcone(R.drawable.monumento);
			lugar.setCodigo(linha.get("Código"));
			lugar.setNome(linha.get("Nome"));
			lugar.setHistorico(linha.get("Histórico"));
			lugar.setUrlFoto(linha.get("Foto"));
			lugar.setAutor(linha.get("Autor"));
			lugar.setInauguracao(linha.get("Inauguração"));
			lugar.setLocalizacao(linha.get("Localização"));
			lugar.setLatitude(linha.get("Latitude"));
			lugar.setLongitude(linha.get("Longitude"));

			lugares.add(lugar);
			linha = keyed.next();
		}

		return lugares;
	}

	@Override
	public Set<MuseuDTO> obterMuseus() throws IOException {
		Set<MuseuDTO> lugares = new HashSet<>();

		InputStream arquivo = ctx.getResources().openRawResource(R.raw.museus);
		CsvReader.Keyed keyed = new CsvReader(arquivo).keyed();

		CsvReader.Keyed.Line linha = keyed.next();

		while (linha != null) {
			MuseuDTO lugar = new MuseuDTO();

			lugar.setIcone(R.drawable.museu);
			lugar.setNome(linha.get("Nome"));
			lugar.setEndereco(linha.get("Endereço"));
			lugar.setNumero(linha.get("Número"));
			lugar.setBairro(linha.get("Bairro"));
			lugar.setTelefone(linha.get("Telefone"));
			lugar.setLatitude(linha.get("latitude"));
			lugar.setLongitude(linha.get("longitude"));

			lugares.add(lugar);
			linha = keyed.next();
		}

		return lugares;
	}

	@Override
	public Set<PraiaDTO> obterPraias() throws IOException {
		Set<PraiaDTO> lugares = new HashSet<>();

		InputStream arquivo = ctx.getResources().openRawResource(R.raw.praias);
		CsvReader.Keyed keyed = new CsvReader(arquivo).keyed();

		CsvReader.Keyed.Line linha = keyed.next();

		while (linha != null) {
			PraiaDTO lugar = new PraiaDTO();

			lugar.setIcone(R.drawable.praia);
			lugar.setNome(linha.get("Nome"));
			lugar.setEndereco(linha.get("Endereço"));
			lugar.setNumero(linha.get("Número"));
			lugar.setBairro(linha.get("Bairro"));
			lugar.setTelefone(linha.get("Telefone"));
			lugar.setLatitude(linha.get("latitude"));
			lugar.setLongitude(linha.get("longitude"));

			lugares.add(lugar);
			linha = keyed.next();
		}

		return lugares;
	}

}
