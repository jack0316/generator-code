package com.jack.generatorcode.template.destractor;

import java.util.ArrayList;
import java.util.List;

public class JavaFiledDescriptor {

    private String fieldType;

    private List<String> fieldDescriptions;

    private List<String> annotations;

    private String modifier;

    private String fieldName;

    public JavaFiledDescriptor(String fieldType, String modifier, String fieldName) {
        this.annotations = new ArrayList<>();
        this.fieldDescriptions = new ArrayList<>();
        this.fieldType = fieldType;
        this.modifier = modifier;
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getFieldDescriptions() {
        return fieldDescriptions;
    }

    public void setFieldDescriptions(List<String> fieldDescriptions) {
        this.fieldDescriptions = fieldDescriptions;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

}
