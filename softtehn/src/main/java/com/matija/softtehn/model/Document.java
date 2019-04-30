package com.matija.softtehn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matija.softtehn.model.converters.MapToJsonConverter;
import com.matija.softtehn.model.embeddables.DateTime;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentId;

    private String templateName;

    @Embedded
    private DateTime dateTime;

    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> data;

    @ManyToOne
    @JoinColumn(name = "template_id")
    @JsonIgnore
    private Template template;

    public Document() {
    }

    public Document(DateTime dateTime, Map<String, Object> data) {
        this.dateTime = dateTime;
        this.data = data;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}