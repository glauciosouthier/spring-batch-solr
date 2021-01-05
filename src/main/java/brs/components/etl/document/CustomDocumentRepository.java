package brs.components.etl.document;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomDocumentRepository {
	public HighlightPage<Document> findDocuments(String searchTerm, Pageable page);
    
    public void deleteByTimestamp(Long timestamp, String baseName) throws Exception;
        
	public void deleteByTimestamp(Long timestamp) throws Exception;
}
