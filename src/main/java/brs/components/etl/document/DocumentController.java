package brs.components.etl.document;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import brs.components.etl.OffsetPageRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/document")
@AllArgsConstructor
public class DocumentController {
	@Autowired
    private DocumentRepository repository;

    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getDocuments(
        @RequestParam String searchTerm,
        @RequestParam(defaultValue = "0") int offset,
        @RequestParam(defaultValue = "10") int limit) {
        HighlightPage<Document> page = repository.findDocuments(searchTerm, new OffsetPageRequest(offset, limit));
        return new ResponseEntity<>(page
            .stream()
            .map(document -> getResult(document, page.getHighlights(document)))
            .collect(Collectors.toList()), getHeaders(page), HttpStatus.OK);
    }

    private DocumentDTO getResult(Document document, List<HighlightEntry.Highlight> highlights) {
        Map<String, List<String>> highlightMap = highlights.stream().collect(Collectors.toMap(h -> h.getField().getName(), HighlightEntry.Highlight::getSnipplets));
        return new DocumentDTO(document.getId(), document.getLastModified(), document, document.getScore(), highlightMap);
    }

    private HttpHeaders getHeaders(Page<?> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Elements", Long.toString(page.getTotalElements()));
        return headers;
    }
}
