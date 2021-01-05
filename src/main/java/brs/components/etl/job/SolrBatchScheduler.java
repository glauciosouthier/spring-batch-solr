package brs.components.etl.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class SolrBatchScheduler {
    private JobLauncher jobLauncher;
    private Job indexDocumentsJob;

   // @Scheduled(cron = "${batch.cron}")
    @Scheduled(fixedDelay = 60000)
    public void schedule() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(indexDocumentsJob, new JobParametersBuilder()
            .addDate("date", new Date())
            .toJobParameters());
    }
}
