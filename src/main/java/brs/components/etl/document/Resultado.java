package brs.components.etl.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultado implements Serializable {

	private static final long serialVersionUID = -6701938463562151775L;

	private Document document;

	private Long totalFound = 0l;

	private Long size = 0l;

	private Long docsPerLista;

	private Long qtdBases;

	private Long qtdPaginas;

	private List<Document> lista;

	private List<Object> resultadosAgrupados;

	private Long pagina;

	public Resultado() {

	}

	public Resultado(Document document, Long totalFound) {
		this.document = document;
		this.totalFound = totalFound;
		this.size = 1l;
	}

	public Resultado(List<Document> lista, Long totalFound, Long docsPerLista) {
		this.lista = lista;
		this.docsPerLista = docsPerLista;
		this.totalFound = totalFound;
		this.size = (long) lista.size();
	}

	public Document getDocument() {
		return document;
	}

	public Long getTotalFound() {
		return totalFound;
	}

	public void setTotalFound(Long quant) {
		this.totalFound = quant;
	}

	public Long getSize() {
		return size;
	}

	public Long getDocsPerLista() {
		return docsPerLista;
	}

	public List<Document> getLista() {
		return lista;
	}

	public void setDocsPerLista(Long docsPerLista) {
		this.docsPerLista = docsPerLista;
	}

	public List<Object> getResultadosAgrupados() {
		if (resultadosAgrupados == null) {
			resultadosAgrupados = new ArrayList<Object>();
		}
		return resultadosAgrupados;
	}

	public void setResultadosAgrupados(List<Object> resultadosAgrupados) {
		this.resultadosAgrupados = resultadosAgrupados;
	}

	public Long getQtdBases() {
		return qtdBases;
	}

	public void setQtdBases(Long qtdBases) {
		this.qtdBases = qtdBases;
	}

	public Long getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(Long qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}
}