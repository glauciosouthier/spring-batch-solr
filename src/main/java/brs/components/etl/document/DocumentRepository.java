package brs.components.etl.document;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.HighlightQueryResult;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface DocumentRepository extends SolrCrudRepository<Document, String>, CustomDocumentRepository {
	@Query("(*?0*)[base]")
	@Highlight(prefix = "<strong>", postfix = "</strong>",fields ="*")
	public HighlightQueryResult<Document> findByBase(String base);

	@Query("(*?0*)[base] E (*?1*)[tipo_documento]")
	@Highlight(prefix = "<strong>", postfix = "</strong>",fields ="*")
	public HighlightQueryResult<Document> consultaPorBaseTipoDocumento(String base, String tipo_documento);

	@Query("(*?0*)[base] E (*?1*)[tipo_documento]")
	@Highlight(prefix = "<strong>", postfix = "</strong>",fields ="*")
	public HighlightPage<Document> consultaPorBaseTipoDocumento(String base, String tipo_documento, Pageable pageable);
	
	@Override
	public void deleteByTimestamp(Long timestamp, String baseName) throws Exception;
	@Override
	public void deleteByTimestamp(Long timestamp) throws Exception;
}
