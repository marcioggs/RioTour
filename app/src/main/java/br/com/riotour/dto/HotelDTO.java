package br.com.riotour.dto;

/**
 * Classe responsável pela modelagem do hotel.
 */
public class HotelDTO extends LugarDTO {

	private String logradouro;
	private String numero;
	private String bairro;
	private String fax;
	private String categoria;
	private String qtdAcomodacoesCadeirante;
	private String qtdAcomodacoesCaoGuia;
	private String telefone;
	private String cnpj;
	private String idiomasFalados;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getQtdAcomodacoesCadeirante() {
		return qtdAcomodacoesCadeirante;
	}

	public void setQtdAcomodacoesCadeirante(String qtdAcomodacoesCadeirante) {
		this.qtdAcomodacoesCadeirante = qtdAcomodacoesCadeirante;
	}

	public String getQtdAcomodacoesCaoGuia() {
		return qtdAcomodacoesCaoGuia;
	}

	public void setQtdAcomodacoesCaoGuia(String qtdAcomodacoesCaoGuia) {
		this.qtdAcomodacoesCaoGuia = qtdAcomodacoesCaoGuia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIdiomasFalados() {
		return idiomasFalados;
	}

	public void setIdiomasFalados(String idiomasFalados) {
		this.idiomasFalados = idiomasFalados;
	}
}
