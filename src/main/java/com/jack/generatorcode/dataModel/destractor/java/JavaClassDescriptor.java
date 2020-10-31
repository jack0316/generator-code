package com.jack.generatorcode.dataModel.destractor.java;

import java.util.ArrayList;
import java.util.List;

public class JavaClassDescriptor {


    private final String className;

    private final String packageName;

    private final String modifier;

    private final List<String> identifiers;

    private final List<String> descriptions;

    private String superClassNames;

    private final List<String> interfaceNames;

    private final List<String> annotationNames;

    private final List<String> imports;

    private final List<JavaFiledDescriptor> fields;

    private final List<JavaMethodDescriptor> methods;

    public JavaClassDescriptor(String className, String packageName, String modifier) {
        this.annotationNames = new ArrayList<>();
        this.interfaceNames = new ArrayList<>();
        this.imports = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.identifiers = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.className = className;
        this.packageName = packageName;
        this.modifier = modifier;
        this.superClassNames = null;
    }


    public JavaClassDescriptor addDescription(String description) {
        this.descriptions.add(description);
        return this;
    }

    public JavaClassDescriptor addImport(String importName) {
        this.imports.add(importName);
        return this;
    }

    public JavaClassDescriptor addField(JavaFiledDescriptor filedDescriptor) {
        this.fields.add(filedDescriptor);
        return this;
    }

    public JavaClassDescriptor addIdentifier(String identifier) {
        this.identifiers.add(identifier);
        return this;
    }

    public JavaClassDescriptor addAnnotation(String annotation) {
        this.annotationNames.add(annotation);
        return this;
    }

    public JavaClassDescriptor addInterfaceName(String interfaceName){
        this.interfaceNames.add(interfaceName);
        return this;
    }

    public JavaClassDescriptor addSuperClassName(String superClassName){
        this.superClassNames = superClassName;
        return this;
    }

    public JavaClassDescriptor addMethod(JavaMethodDescriptor method){
        methods.add(method);
        return this;
    }


    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getModifier() {
        return modifier;
    }

    public List<JavaFiledDescriptor> getFields() {
        return fields;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public String getSuperClassNames() {
        return superClassNames;
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }

    public List<String> getAnnotationNames() {
        return annotationNames;
    }

    public List<String> getImports() {
        return imports;
    }

    public List<JavaMethodDescriptor> getMethods() {
        return methods;
    }
}
