package brs.components.etl.process;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import brs.components.etl.document.BrsDocumentRepository;
import brs.components.etl.document.Document;
import brs.components.etl.document.DocumentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
//@StepScope
public class DocumentReader extends RepositoryItemReader<Document> {
	@Autowired
	private BrsDocumentRepository brsDocumentRepository;

	@PostConstruct
	public void initialize() throws IOException {
		log.info("init reader");
		Map<String, Direction> sortMap = new HashMap<>();
		sortMap.put("id", Direction.DESC);
		this.setRepository(brsDocumentRepository);
		this.setMethodName("consultaPorQuery");
		this.setArguments(Arrays.asList("(trf1)[base]"));
		this.setPageSize(200);
		this.setSort(sortMap);
		this.setSaveState(false);

	}

	@Override
	public Document read() throws Exception, UnexpectedInputException, ParseException {
		// log.info("read");
		return super.read();
	}

	
}
