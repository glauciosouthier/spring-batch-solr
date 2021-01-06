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
	
	
	@JsonProperty("id")//atributo para RestClientSolr
	@Id
	@Indexed(name = "id")//atributo para spring-data-solr
	private String id;
	

	@JsonProperty("docn")
	@Indexed(name = "docn")
	private String docn;
	

	@JsonProperty("classe")
	@Indexed(name = "classe")
	private String clas;
	
	@XmlElement(name = "ORIG")
	@JsonProperty("origem")
	@Indexed(name = "origem")
	private String orig;
	
	
	@JsonProperty("processo")
	@Indexed(name = "processo")
	private String proc;
	
	
	@JsonProperty("uf")
	@Indexed(name = "uf")
	private String uf;
	
	
	@JsonProperty("orgao_julgador")
	@Indexed(name = "orgao_julgador")
	private String orga;
	
	
	@JsonProperty("data_decisao")
	@Indexed(name = "data_decisao")
	private String dtde;
	
	
	@JsonProperty("data_decisao_pesq")
	@Indexed(name = "data_decisao_pesq")
	private String dtdp;

	
	@JsonProperty("fonte_publicacao")
	@Indexed(name = "fonte_publicacao")
	private String font;
	
	
	@JsonProperty("font1")
	private String font1;

	
	@JsonProperty("font2")
	private String font2;
	
	
	@JsonProperty("font3")
	private String font3;
	

	@JsonProperty("decisao")
	@Indexed(name = "decisao")
	private String deci;
	
	
	@JsonProperty("observacoes")
	@Indexed(name = "observacoes")
	private String obs;
	
	
	@JsonProperty("ementa")
	@Indexed(name = "ementa")
	private String emen;
	
	
	@JsonProperty("indexacao")
	@Indexed(name = "indexacao")
	private String inde;
	
	
	@JsonProperty("data_publicacao_pesq")
	@Indexed(name = "data_publicacao_pesq")
	private String dtpp;
	
	@JsonProperty("relator")
	@Indexed(name = "relator")
	private String rel;
	
	@JsonProperty("relator_acordao")
	@Indexed(name = "relator_acordao")
	private String rela;
	
	
	@JsonProperty("revisor")
	@Indexed(name = "revisor")
	private String rev;

	
	@JsonProperty("relator_convocado")
	@Indexed(name = "relator_convocado")
	private String relc;

	
	@JsonProperty("outras_fontes")
	@Indexed(name = "outras_fontes")
	private String outf;

	
	@JsonProperty("precedentes")
	@Indexed(name = "precedentes")
	private String prec;


	@JsonProperty("sucessivos")
	@Indexed(name = "sucessivos")
	private String suce;

	
	@JsonProperty("doutrina")
	@Indexed(name = "doutrina")
	private String dout;

	
	@JsonProperty("catalogo")
	@Indexed(name = "catalogo")
	private String cata;

	
	@JsonProperty("ref_legislativa")
	@Indexed(name = "ref_legislativa")
	private String refl;

	
	@JsonProperty("processo_formatado")
	@Indexed(name = "processo_formatado")
	private String prfo;

	
	@JsonProperty("outras_referencias")
	@Indexed(name = "outras_referencias")
	private String oure;

	
	@JsonProperty("txt_origem")
	@Indexed(name = "txt_origem")
	private String txto;

	@XmlTransient
	private String dtap;

	@XmlTransient
	private String hisa;

	
	@JsonProperty("tribunal")
	@Indexed(name = "tribunal")
	private String trib;

	
	@JsonProperty("objeto_processo")
	@Indexed(name = "objeto_processo")
	private String obpr;

	@XmlElement(name = "RESP")
	@JsonProperty("relator_suplente")
	@Indexed(name = "relator_suplente")
	private String resp;

	
	@JsonProperty("link_inteiro_teor")
	@Indexed(name = "link_inteiro_teor")
	private String lite;

	
	@JsonProperty("inteiro_teor")
	@Indexed(name = "inteiro_teor")
	private String iteo;
	
	
	// @JsonProperty("INTEIRO_TEOR")
	//copiado pelo solr
	private String inte;
	

	@JsonProperty("tipo_documento")
	@Indexed(name = "tipo_documento")
	private String tipo;


	@Indexed(name = "base")
	private String base;
	
	
	private String pftr;

	
	@JsonProperty("sigla_classe")
	@Indexed(name = "sigla_classe")
	private String sigc;
	
	@Indexed(name = "timestamp")
	private Long timestamp;
        
    @Indexed("last_modified")
    private LocalDateTime lastModified;
    
    @Score
    private float score;
}
