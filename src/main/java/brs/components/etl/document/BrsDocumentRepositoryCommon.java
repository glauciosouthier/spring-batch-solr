package brs.components.etl.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrsDocumentRepositoryCommon {
	Page<Document> consultaPorQuery(String query, Pageable pageable)throws Exception;
}
