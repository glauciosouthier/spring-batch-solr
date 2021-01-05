package brs.components.etl.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BrsDocumentRepository extends PagingAndSortingRepository<Document, String>,BrsDocumentRepositoryCommon {
	@Override
	public Page<Document> consultaPorQuery(String query, Pageable pageable)throws Exception;
}
