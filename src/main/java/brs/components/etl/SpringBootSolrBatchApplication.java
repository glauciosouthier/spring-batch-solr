package brs.components.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootSolrBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSolrBatchApplication.class, args);
    }
}
