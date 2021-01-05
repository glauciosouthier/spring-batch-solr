package brs.components.etl.process;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import brs.components.etl.SolrItemWriterException;
import brs.components.etl.document.Document;
import brs.components.etl.document.DocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class DocumentWriter implements ItemWriter<Document> {
	
	final private SolrClient solrClient;
	final private DocumentRepository documentRepository;
	@Override
	public void write(List<? extends Document> list) {
		list.forEach(this::save);

	}

	private void save(Document doc) {
		try {
			log.info("save Document:" + doc.getId());
			 documentRepository.save(doc);
			//solrClient.addBean(Document.COLLECTION_NAME, doc);
			//log.info("Updated document in Solr: {}", doc.getId());
		} catch (Exception ex) {
			throw new SolrItemWriterException("Could not index document", ex);
		}
	}
}
