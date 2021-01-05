package brs.components.etl.process;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import brs.components.etl.document.Document;
import brs.components.etl.job.SolrBatchConfig;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DocumentProcessor implements ItemProcessor<Document, Document> {
	private static final String ANO_MES_DIA = "yyyyMMdd";
	private static final String DIA_MES_ANO = "dd/MM/yyyy";
	private static final String PADRAO_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	// private Parser parser;
	// private HtmlRenderer htmlRenderer;

	@PostConstruct
	public void initialize() {
		// List<Extension> extensions =
		// Collections.singletonList(TablesExtension.create());
		// parser = Parser.builder().extensions(extensions).build();
		// htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();
	}

	@Override
	public Document process(Document docBrs) throws IOException {
		//log.info("process Document:"+docBrs.getId());
		/*
		 * try (InputStreamReader reader = new
		 * InputStreamReader(markdownResource.getInputStream())) { Node document =
		 * parser.parseReader(reader); return new HtmlResource(markdownResource,
		 * htmlRenderer.render(document)); }
		 */
		docBrs.setDocn(null);// deixar o solr setar o docn
		docBrs.setTrib(preparaTexto(docBrs.getTrib()));
		docBrs.setOrga(preparaTexto(docBrs.getOrga()));
		docBrs.setObpr(preparaTexto(docBrs.getObpr()));

		docBrs.setRel(preparaTexto(docBrs.getRel()));
		docBrs.setRev(preparaTexto(docBrs.getRev()));
		docBrs.setRela(preparaTexto(docBrs.getRela()));
		docBrs.setRelc(preparaTexto(docBrs.getRelc()));
		docBrs.setResp(preparaTexto(docBrs.getResp()));

		docBrs.setClas(preparaTexto(docBrs.getClas()));
		docBrs.setSigc(preparaTexto(docBrs.getSigc()));

		docBrs.setDtde(toUTC(docBrs.getDtde(), DIA_MES_ANO));
		docBrs.setDtdp(toInvertedDate(docBrs.getDtde(), DIA_MES_ANO));
		docBrs.setDtpp(toUTC(docBrs.getDtpp(), ANO_MES_DIA));

		docBrs.setIteo(preparaTexto(coalesce(docBrs.getIteo(), docBrs.getInte())));
		docBrs.setEmen(preparaTexto(docBrs.getEmen()));
		docBrs.setDeci(preparaTexto(docBrs.getDeci()));
		docBrs.setTxto(preparaTexto(docBrs.getTxto()));
		return docBrs;
	

	}

	private String preparaTexto(String txt) {
		// return charset(txt);
		return txt;
	}

	private String coalesce(String... lista) {
		if (lista == null)
			return null;
		for (String item : lista) {
			if (!Strings.isBlank(item))
				return item;
		}
		return lista.length > 0 ? lista[0] : null;
	}

	private static String toUTC(String dtde, String padraoEntrada) {
		return formataData(dtde, padraoEntrada, PADRAO_UTC);
	}

	private static String toInvertedDate(String dtde, String padraoEntrada) {
		return formataData(dtde, padraoEntrada, ANO_MES_DIA);
	}

	private static String formataData(String dtde, String padraoEntrada, String padraoSaida) {
		try {
			SimpleDateFormat formatador = new SimpleDateFormat(padraoEntrada);
			Date dateToConvert = formatador.parse(dtde);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(padraoSaida);
			return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).format(formatter);
		} catch (Exception e) {
		}
		return dtde;
	}

	private LocalDate toLocalDate(Date dateToConvert) {
		// if (dateToConvert instanceof java.sql.Date)
		// return ((java.sql.Date) dateToConvert).toLocalDate();
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
