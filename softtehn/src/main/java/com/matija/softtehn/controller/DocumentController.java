package com.matija.softtehn.controller;

import com.matija.softtehn.model.Document;
import com.matija.softtehn.model.UserPrincipal;
import com.matija.softtehn.service.DocumentService;
import com.matija.softtehn.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
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
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id, HttpServletRequest request){
        Document document = documentService.getDocument(id);
        Resource resource = fss.loadFileAsResource(document.getFileName());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
