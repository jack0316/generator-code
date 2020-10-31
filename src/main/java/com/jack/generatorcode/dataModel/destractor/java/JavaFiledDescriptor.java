package com.jack.generatorcode.dataModel.destractor.java;

import java.util.ArrayList;
import java.util.List;

public class JavaFiledDescriptor {

    private final String fieldType;

    private final List<String> fieldDescriptions;

    private final List<String> annotations;

    private final List<String> identifiers;

    private final String modifier;

    private final String fieldName;

    private String value;

    public JavaFiledDescriptor(String modifier,String fieldType,  String fieldName) {
        this.annotations = new ArrayList<>();
        this.fieldDescriptions = new ArrayList<>();
        this.identifiers = new ArrayList<>();
        this.fieldType = fieldType;
        this.modifier = modifier;
        this.fieldName = fieldName;
    }

    public JavaFiledDescriptor addDescription(String description){
        this.fieldDescriptions.add(description);
        return this;
    }

    public JavaFiledDescriptor addAnnotation(String annotation){
        annotations.add(annotation);
        return this;
    }

    public JavaFiledDescriptor addIdentifier(String identifier){
        identifiers.add(identifier);
        return this;
    }

    public JavaFiledDescriptor setValue(String value) {
        this.value = value;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public List<String> getFieldDescriptions() {
        return fieldDescriptions;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public String getModifier() {
        return modifier;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }
}
