package brs.components.etl.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;

@AllArgsConstructor
@Data
public class HtmlResource {
    private Resource resource;
    private String html;
}
