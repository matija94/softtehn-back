package com.matija.softtehn.controller;

public final class Urls {

    private Urls() {}

    public static final String LOGIN = "/login";

    public static final String TEMPLATE= "/template";
    public static final String TEMPLATE_BY_NAME = "/template/{name}";

    public static final String DOCUMENT = "/document";
    public static final String DOCUMENT_UPLOAD = "/document/upload";
    public static final String DOCUMENT_BY_TEMPLATE_NAME = "/document/{template_name}";
    public static final String DOCUMENT_FILE_BY_ID = "/document/{id}/file";
}
