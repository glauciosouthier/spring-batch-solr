package brs.components.etl.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import brs.components.etl.document.Document;

@Component
@AllArgsConstructor
@Slf4j
public class DocumentOptimizeTasklet implements Tasklet {
    private SolrClient solrClient;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    	log.info("DocumentOptimizeTasklet.execute");
    	solrClient.optimize(Document.COLLECTION_NAME);
        return RepeatStatus.FINISHED;
    }
}
