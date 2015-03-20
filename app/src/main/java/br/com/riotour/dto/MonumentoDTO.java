package br.com.riotour.dto;

/**
 * Classe responsável pela modelagem do monumento.
 */
public class MonumentoDTO extends LugarDTO {

	private String codigo;
	private String historico;
	private String urlFoto;
	private String autor;
	private String inauguracao;
	private String localizacao;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getInauguracao() {
		return inauguracao;
	}

	public void setInauguracao(String inauguracao) {
		this.inauguracao = inauguracao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
}
