package com.matija.softtehn.controller;

import com.matija.softtehn.model.Document;
import com.matija.softtehn.model.UserPrincipal;
import com.matija.softtehn.service.DocumentService;
import com.matija.softtehn.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private FileStorageService fss;

    @PostMapping(Urls.DOCUMENT)
    public ResponseEntity<Document> createDocument(@RequestBody  Document document) {
        documentService.createDocument(document);
        return new ResponseEntity(document, HttpStatus.CREATED);
    }

    @PostMapping(Urls.DOCUMENT_UPLOAD)
    public String storeFile(@RequestParam("file") MultipartFile file) {
        return fss.storeFile(file);
    }

    @GetMapping(Urls.DOCUMENT_BY_TEMPLATE_NAME)
    public ResponseEntity<List<Document>> getDocumentsByTemplateName(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable("template_name") String templateName) {

        List<Document> documents = documentService.findDocumentsByGroupAndTemplateName(
                principal.getUser().getGroup(), templateName);
        return new ResponseEntity(documents, HttpStatus.OK);
    }

    @GetMapping(Urls.DOCUMENT_FILE_BY_ID)
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id){
        Document document = documentService.getDocument(id);
        byte[] data = fss.loadFileAsBytes(document.getFileName());

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf("application/pdf"));
        header.setContentLength(data.length);
        header.set("Content-Disposition", "inline;filename=" + document.getFileName());
        return new ResponseEntity<>(data, header, HttpStatus.OK);
    }


}
