package com.matija.softtehn.controller;

import com.matija.softtehn.model.Template;
import com.matija.softtehn.model.UserPrincipal;
import com.matija.softtehn.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping(Urls.TEMPLATE)
    public ResponseEntity<List<Template>> getTemplates(@AuthenticationPrincipal UserPrincipal principal) {
        return new ResponseEntity(templateService.findByGroup(principal.getUser().getGroup()), HttpStatus.OK);
    }

    @GetMapping(Urls.TEMPLATE_BY_NAME)
    public ResponseEntity<Template> getTemplateByName(@AuthenticationPrincipal UserPrincipal principal,
                                                      @PathVariable("name") String name) {
        Template template = templateService.findDocumentsByGroupAndTemplateName(principal.getUser().getGroup(),
                name);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @PostMapping(Urls.TEMPLATE)
    public ResponseEntity<Template> createTemplate(@AuthenticationPrincipal UserPrincipal principal,
                                                   @RequestBody Template template) {
        templateService.createTemplate(principal.getUser(), template);
        return new ResponseEntity(template, HttpStatus.CREATED);
    }


}
