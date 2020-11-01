package com.jack.generatorcode.dataModel.destractor.java;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaMethodDescriptor {

    private final String methodName;

    private final String returnType;

    private final String modifier;

    private final boolean abstractMethod;

    private List<String> descriptions;

    private List<String> annotations;

    private List<String> bodies;

    private List<String> identifiers;

    private Map<String,String> params;

    private Map<String,String> paramAnnotations;

    public JavaMethodDescriptor(String modifier, String returnType,String methodName,  boolean abstractMethod) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.modifier = modifier;
        this.abstractMethod = abstractMethod;
        annotations = new ArrayList<>();
        descriptions = new ArrayList<>();
        bodies = new ArrayList<>();
        params = new HashMap<>();
        paramAnnotations = new HashMap<>();
        identifiers = new ArrayList<>();
    }

    public JavaMethodDescriptor addDescription(String description){
        descriptions.add(description);
        return this;
    }

    public JavaMethodDescriptor addAnnotation(String annotation){
        annotations.add(annotation);
        return this;
    }

    public JavaMethodDescriptor addBody(String body){
        bodies.add(body);
        return this;
    }

    public JavaMethodDescriptor addParams(String paramName,String paramType,String paramAnnotation){
        params.put(paramName,paramType);
        if (!StringUtils.isEmpty(paramAnnotation)){
            paramAnnotations.put(paramName,paramAnnotation);
        }
        return this;
    }

    public JavaMethodDescriptor addIdentifier(String identifier){
        identifiers.add(identifier);
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getModifier() {
        return modifier;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public List<String> getBodies() {
        return bodies;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getParamAnnotations() {
        return paramAnnotations;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    public void setBodies(List<String> bodies) {
        this.bodies = bodies;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setParamAnnotations(Map<String, String> paramAnnotations) {
        this.paramAnnotations = paramAnnotations;
    }
}
