package brs.components.etl.job;

import java.util.concurrent.ThreadPoolExecutor;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimplePartitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import brs.components.etl.document.Document;
import brs.components.etl.process.DocumentOptimizeTasklet;
import brs.components.etl.process.DocumentProcessor;
import brs.components.etl.process.DocumentReader;
import brs.components.etl.process.DocumentWriter;
import brs.components.etl.process.DocumentsDeleteTasklet;
import brs.components.etl.process.JobCompletionListener;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
@EnableScheduling
@Slf4j
@EnableConfigurationProperties(SolrBatchConfigurationProperties.class)
public class SolrBatchConfig {
    @Bean
    public Job indexDocumentsJob(JobBuilderFactory jobBuilderFactory, Step deleteStep,Step indexingStep, Step optimizeStep,Step partitionStep) {
    	log.info("config indexDocumentsJob");
    	return jobBuilderFactory.get("indexDocumentsJob")
            .incrementer(new RunIdIncrementer())
            .flow(deleteStep)
            //
            //.next(indexingStep)
            //estrategia partition step
            //see https://docs.spring.io/spring-batch/docs/current/reference/html/scalability.html
            .next(partitionStep)
            //
            .next(optimizeStep)
            .end()
            .build();
    }
    @Bean
    public Step deleteStep(StepBuilderFactory stepBuilderFactory, DocumentsDeleteTasklet tasklet) {
    	log.info("config deleteStep");
    	return stepBuilderFactory.get("deleteStep").tasklet(tasklet).build();
    }
    
    @Bean
    public Step partitionStep(StepBuilderFactory stepBuilderFactory, Step indexingStep) {
    	log.info("config partitionStep");
    	return stepBuilderFactory.get("partitionStep")
    			.partitioner("indexingStep", partitioner())
    		    .partitionHandler(partitionHandler(indexingStep))
            .build();
    }
    @Bean
    public Partitioner partitioner() {
    	return new SimplePartitioner();
    }
    @Bean
    public PartitionHandler partitionHandler(Step indexingStep) {
        TaskExecutorPartitionHandler retVal = new TaskExecutorPartitionHandler();
        retVal.setTaskExecutor(taskExecutor());
        retVal.setStep(indexingStep);
        retVal.setGridSize(10);
        return retVal;
    }
    
    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("spring_batch");
    }
   
    @Bean
    public Step indexingStep(StepBuilderFactory stepBuilderFactory, DocumentReader reader, DocumentProcessor processor, DocumentWriter writer,TaskExecutor taskExecutor) {
    	log.info("config indexingStep");
    	return stepBuilderFactory.get("indexingStep")
            .<Document, Document> chunk(200)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            //estrategia  Multi-threaded Step
            //.taskExecutor(taskExecutor)
            //.throttleLimit(10)
            .build();
    }

    @Bean
    public Step optimizeStep(StepBuilderFactory stepBuilderFactory, DocumentOptimizeTasklet tasklet) {
    	log.info("config optimizeStep");
    	return stepBuilderFactory.get("optimizeStep")
            .tasklet(tasklet)
            .build();
    }

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient) {
        return new SolrTemplate(solrClient);
    }
    @Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}
