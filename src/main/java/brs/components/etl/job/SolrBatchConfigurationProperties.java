package brs.components.etl.job;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch")
@Data
public class SolrBatchConfigurationProperties {
    private String pathPattern;
    private String extractPath;
    private String cron;
}
