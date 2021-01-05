package brs.components.etl.document;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;
import org.springframework.data.solr.repository.Score;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@SolrDocument(collection = Document.COLLECTION_NAME)
public class Document {
	public static final String COLLECTION_NAME = "juris";
	
	@XmlElement(name = "ID") //atributo para ws-soap
	@JsonProperty("id")//atributo para RestClientSolr
	@Id
	@Indexed(name = "id", type = "string")//atributo para spring-data-solr
	private String id;
	
	@XmlElement(name = "DOCN")
	@JsonProperty("docn")
	@Indexed(name = "docn", type = "string")
	private String docn;
	
	@XmlElement(name = "CLAS")
	@JsonProperty("classe")
	@Indexed(name = "classe", type = "string")
	private String clas;
	
	@XmlElement(name = "ORIG")
	@JsonProperty("origem")
	@Indexed(name = "origem", type = "string")
	private String orig;
	
	@XmlElement(name = "PROC")
	@JsonProperty("processo")
	@Indexed(name = "processo", type = "string")
	private String proc;
	
	@XmlElement(name = "UF")
	@JsonProperty("uf")
	@Indexed(name = "uf", type = "string")
	private String uf;
	
	@XmlElement(name = "ORGA")
	@JsonProperty("orgao_julgador")
	@Indexed(name = "orgao_julgador", type = "string")
	private String orga;
	
	@XmlElement(name = "DTDE")
	@JsonProperty("data_decisao")
	@Indexed(name = "data_decisao", type = "string")
	private String dtde;
	
	@XmlElement(name = "DTDP")
	@JsonProperty("data_decisao_pesq")
	@Indexed(name = "data_decisao_pesq", type = "string")
	private String dtdp;

	@XmlElement(name = "FONT")
	@JsonProperty("fonte_publicacao")
	@Indexed(name = "fonte_publicacao", type = "string")
	private String font;
	
	@XmlElement(name = "FONT1")
	@JsonProperty("font1")
	private String font1;

	@XmlElement(name = "FONT2")
	@JsonProperty("font2")
	private String font2;
	
	@XmlElement(name = "FONT3")
	@JsonProperty("font3")
	private String font3;
	
	private String rel;
	
	@XmlElement(name = "DECI")
	@JsonProperty("decisao")
	@Indexed(name = "decisao", type = "string")
	private String deci;
	
	@XmlElement(name = "OBS")
	@JsonProperty("observacoes")
	@Indexed(name = "observacoes", type = "string")
	private String obs;
	
	@XmlElement(name = "EMEN")
	@JsonProperty("ementa")
	@Indexed(name = "ementa", type = "string")
	private String emen;
	
	@XmlElement(name = "INDE")
	@JsonProperty("indexacao")
	@Indexed(name = "indexacao", type = "string")
	private String inde;
	
	@XmlElement(name = "DTPP")
	@JsonProperty("data_publicacao_pesq")
	@Indexed(name = "data_publicacao_pesq", type = "string")
	private String dtpp;
	/**
	 * Atributo relativo ao parágrafo RELA
	 */
	@XmlElement(name = "RELA")
	@JsonProperty("relator")
	@Indexed(name = "relator", type = "string")
	private String rela;
	
	@XmlElement(name = "REV")
	@JsonProperty("revisor")
	@Indexed(name = "revisor", type = "string")
	private String rev;

	@XmlElement(name = "RELC")
	@JsonProperty("relator_convocado")
	@Indexed(name = "relator_convocado", type = "string")
	private String relc;

	@XmlElement(name = "OUTF")
	@JsonProperty("outras_fontes")
	@Indexed(name = "outras_fontes", type = "string")
	private String outf;

	@XmlElement(name = "PREC")
	@JsonProperty("precedentes")
	@Indexed(name = "precedentes", type = "string")
	private String prec;

	@XmlElement(name = "SUCE")
	@JsonProperty("sucessivos")
	@Indexed(name = "sucessivos", type = "string")
	private String suce;

	@XmlElement(name = "DOUT")
	@JsonProperty("doutrina")
	@Indexed(name = "doutrina", type = "string")
	private String dout;

	@XmlElement(name = "CATA")
	@JsonProperty("catalogo")
	@Indexed(name = "catalogo", type = "string")
	private String cata;

	@XmlElement(name = "REFL")
	@JsonProperty("ref_legislativa")
	@Indexed(name = "ref_legislativa", type = "string")
	private String refl;

	@XmlElement(name = "PRFO")
	@JsonProperty("processo_formatado")
	@Indexed(name = "processo_formatado", type = "string")
	private String prfo;

	@XmlElement(name = "OURE")
	@JsonProperty("outras_referencias")
	@Indexed(name = "outras_referencias", type = "string")
	private String oure;

	@XmlElement(name = "TXTO")
	@JsonProperty("txt_origem")
	@Indexed(name = "txt_origem", type = "string")
	private String txto;

	@XmlTransient
	private String dtap;

	@XmlTransient
	private String hisa;

	@XmlElement(name = "TRIB")
	@JsonProperty("tribunal")
	@Indexed(name = "tribunal", type = "string")
	private String trib;

	@XmlElement(name = "OBPR")
	@JsonProperty("objeto_processo")
	@Indexed(name = "objeto_processo", type = "string")
	private String obpr;

	@XmlElement(name = "RESP")
	@JsonProperty("relator_suplente")
	@Indexed(name = "relator_suplente", type = "string")
	private String resp;

	@XmlElement(name = "LITE")
	@JsonProperty("link_inteiro_teor")
	@Indexed(name = "link_inteiro_teor", type = "string")
	private String lite;

	@XmlElement(name = "ITEO")
	@JsonProperty("inteiro_teor")
	@Indexed(name = "inteiro_teor", type = "string")
	private String iteo;
	
	@XmlElement(name = "INTE")
	// @JsonProperty("INTEIRO_TEOR")
	//copiado pelo solr
	private String inte;
	
	@XmlElement(name = "TIPO")
	@JsonProperty("tipo_documento")
	@Indexed(name = "tipo_documento", type = "string")
	private String tipo;

	@XmlTransient
	@Indexed(name = "base", type = "string")
	private String base;
	@XmlTransient
	private String pftr;

	@XmlElement(name = "SIGC")
	@JsonProperty("sigla_classe")
	@Indexed(name = "sigla_classe", type = "string")
	private String sigc;
	
	@Indexed(name = "timestamp", type = "long")
	private Long timestamp;
        
    @Indexed("last_modified")
    private LocalDateTime lastModified;
    @Score
    private float score;
}
