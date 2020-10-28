package com.jack.generatorcode.template.destractor;

import java.util.ArrayList;
import java.util.List;

public class JavaClassDescriptor {


    private final String className;

    private final String packageName;

    private final String modifier;

    private final List<String> classType;

    private final List<String> descriptions;

    private final List<String> superClassNames;

    private final List<String> interfaceNames;

    private final List<String> annotationNames;

    private final List<String> imports;

    private final List<JavaFiledDescriptor> fields;

    private final List<JavaMethodDescriptor> methods;

    public JavaClassDescriptor(String className, String packageName, String modifier) {
        this.annotationNames = new ArrayList<>();
        this.superClassNames = new ArrayList<>();
        this.interfaceNames = new ArrayList<>();
        this.imports = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.classType = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.className = className;
        this.packageName = packageName;
        this.modifier = modifier;
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

    public JavaClassDescriptor addClassType(String typeName) {
        this.classType.add(typeName );
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
        this.superClassNames.add(superClassName);
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

    public List<String> getClassType() {
        return classType;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getSuperClassNames() {
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
}
