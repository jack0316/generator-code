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

    private final List<String> descriptions;

    private final List<String> annotations;

    private final List<String> bodies;

    private final List<String> identifiers;

    private final Map<String,String> params;

    private final Map<String,String> paramAnnotations;

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

}
