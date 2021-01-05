package brs.components.etl.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Repository
@Slf4j
public class DocumentRepositoryImpl implements CustomDocumentRepository {
	

	@Autowired
	private SolrTemplate solrTemplate;

	@Override
	public HighlightPage<Document> findDocuments(String searchTerm, Pageable page) {
		SimpleHighlightQuery query = new SimpleHighlightQuery(new SimpleStringCriteria(searchTerm), page);
		query.setHighlightOptions(
				new HighlightOptions().setSimplePrefix("<strong>").setSimplePostfix("</strong>").addField("*"));
		return solrTemplate.queryForHighlightPage(Document.COLLECTION_NAME, query, Document.class);
	}

	@Override
	public void deleteByTimestamp(Long timestamp, String baseName) throws Exception {
		log.info("deleteByTimestamp("+timestamp+","+baseName+")");
		String query = "timestamp:{* TO " + timestamp + "} AND base:" + baseName + " ";
		solrTemplate.delete(Document.COLLECTION_NAME, new SimpleQuery(query));
		solrTemplate.commit(Document.COLLECTION_NAME);
	}
	@Override
	public void deleteByTimestamp(Long timestamp) throws Exception{
		log.info("deleteByTimestamp("+timestamp+")");
		String query = "!timestamp="+timestamp +"";
		solrTemplate.delete(Document.COLLECTION_NAME, new SimpleQuery(query));
		solrTemplate.commit(Document.COLLECTION_NAME);
	}
}
