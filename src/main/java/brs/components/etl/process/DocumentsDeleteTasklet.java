package brs.components.etl.process;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import brs.components.etl.document.DocumentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class DocumentsDeleteTasklet implements Tasklet {
	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		log.info("DocumentsDeleteTasklet.execute->delete");
		documentRepository.deleteByTimestamp( System.currentTimeMillis());
		return RepeatStatus.FINISHED;
	}
}
