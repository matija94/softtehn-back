package com.matija.softtehn.service;

import com.matija.softtehn.model.Document;
import com.matija.softtehn.model.Group;
import com.matija.softtehn.model.Template;
import com.matija.softtehn.model.embeddables.DateTime;
import com.matija.softtehn.repository.DocumentRepository;
import com.matija.softtehn.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private TemplateRepository templateRepository;

    public Document createDocument(Document document) {
        DateTime dateTime = DateTime.createDateTime();
        Template template = templateRepository.findByName(document.getTemplateName());
        document.setTemplate(template);
        document.setDateTime(dateTime);
        documentRepository.save(document);
        return document;
    }

    public List<Document> findDocumentsByGroupAndTemplateName(Group group, String templateName) {
        Template template = templateRepository.findByGroupIDAndTemplateName(group.getGroupId(), templateName);
        return documentRepository.findDocumensByTemplateId(template.getTemplateId());
    }
}
