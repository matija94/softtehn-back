package com.matija.softtehn.model.converters;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;

@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        String json = null;
        ObjectMapper om = new ObjectMapper();
        try {
            json = om.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            System.out.println(e);
        }
        return json;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> data = null;
        try {
            data = om.readValue(dbData, Map.class);
        } catch (final IOException e) {
            System.out.println("JSON reading error");
        }
        return data;
    }
}
