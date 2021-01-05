package brs.components.etl.document;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class DocumentDTO {
    private String id;
    private LocalDateTime lastModified;
    private Document content;
    private float score;
    private Map<String, List<String>> highlights;
}
