package com.jack.generatorcode.template.destractor;

import java.util.ArrayList;
import java.util.List;

public class JavaClassDescriptor {

    private final String LINE = "\n";

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

    public JavaClassDescriptor(String className, String packageName, String modifier) {
        this.annotationNames = new ArrayList<>();
        this.superClassNames = new ArrayList<>();
        this.interfaceNames = new ArrayList<>();
        this.imports = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.classType = new ArrayList<>();
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


    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getModifier() {
        return modifier;
    }

    public String getClassType() {
        StringBuilder stringBuilder = new StringBuilder();
        classType.forEach(type->stringBuilder.append(" ").append(type).append(" "));
        return stringBuilder.toString();
    }

    public String getDescriptions() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptions.forEach(description -> stringBuilder.append(description).append(LINE));
        return stringBuilder.toString();
    }

    public String getSuperClassNames() {
        if (superClassNames.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" extends ");
        for (int i = 0; i < superClassNames.size(); i++) {
            stringBuilder.append(superClassNames.get(i));
            if (i != superClassNames.size() -1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public String getInterfaceNames() {
        if (interfaceNames.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" extends ");
        for (int i = 0; i < interfaceNames.size(); i++) {
            stringBuilder.append(interfaceNames.get(i));
            if (i != interfaceNames.size() -1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public String getAnnotationNames() {
        StringBuilder stringBuilder = new StringBuilder();
        annotationNames.forEach(annotation -> stringBuilder.append(annotation).append(LINE));
        return stringBuilder.toString();
    }

    public String getImports() {
        StringBuilder stringBuilder = new StringBuilder();
        imports.forEach(importName -> stringBuilder.append("import ").append(importName).append(";").append(LINE));
        return stringBuilder.toString();
    }

    public List<JavaFiledDescriptor> getFields() {
        return fields;
    }
}
