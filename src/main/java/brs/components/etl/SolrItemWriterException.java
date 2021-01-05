package brs.components.etl;

import org.springframework.batch.item.ItemWriterException;

public class SolrItemWriterException extends ItemWriterException {
    public SolrItemWriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public SolrItemWriterException(String message) {
        super(message);
    }
}
