package com.jack.generatorcode.template.java;

import com.jack.generatorcode.dataModel.destractor.java.JavaClassDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaFiledDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaMethodDescriptor;
import com.jack.generatorcode.template.TemplateAbstract;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JavaTemplate extends TemplateAbstract {

    protected JavaClassDescriptor descriptor;
    protected final String LINE = "\n";
    protected final String SPACE = " ";
    protected final String END = ";";
    protected final String BLANK = "";

    public JavaTemplate() {
    }

    public JavaTemplate(JavaClassDescriptor descriptor) {
        this.descriptor = descriptor;
    }


    public String getClassType() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptor.getIdentifiers().forEach(identifier->stringBuilder.append(SPACE).append(identifier).append(SPACE));
        return stringBuilder.toString();
    }

    public String getDescriptions() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptor.getDescriptions().forEach(description -> stringBuilder.append(description).append(LINE));
        return stringBuilder.toString();
    }

    public String getSuperClassNames() {
        String superClassNames = this.descriptor.getSuperClassNames();
        if (StringUtils.isEmpty(superClassNames)) return SPACE;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" extends ").append(superClassNames).append(SPACE);
        return stringBuilder.toString();
    }

    public String getInterfaceNames() {
        List<String> interfaceNames = this.descriptor.getInterfaceNames();
        if (interfaceNames.isEmpty()) return SPACE;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" implements ");
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
        this.descriptor.getAnnotationNames().forEach(annotation -> stringBuilder.append(annotation).append(LINE));
        return stringBuilder.toString();
    }

    public String getImports() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptor.getImports().forEach(importName -> stringBuilder.append("import ").append(importName).append(";").append(LINE));
        return stringBuilder.toString();
    }

    public String getFields(){
        List<JavaFiledDescriptor> fields = descriptor.getFields();
        if (fields.isEmpty()) return SPACE;
        StringBuilder stringBuilder = new StringBuilder();
        fields.forEach(f -> {
            f.getFieldDescriptions().forEach(description -> stringBuilder.append(description).append(LINE));
            f.getAnnotations().forEach(annotation -> stringBuilder.append(annotation).append(LINE));
            stringBuilder.append(f.getModifier()).append(SPACE);
            f.getIdentifiers().forEach(identifier->stringBuilder.append(identifier).append(SPACE));
            stringBuilder.append(f.getFieldType()).append(SPACE);
            f.getIdentifiers().forEach(identifier->stringBuilder.append(identifier).append(SPACE));
            stringBuilder.append(f.getFieldName()).append(SPACE);
            if (!StringUtils.isEmpty(f.getValue())) stringBuilder.append("=").append(f.getValue());
            stringBuilder.append(END).append(LINE);
        });
        return stringBuilder.toString();
    }

    public String getMethods(){
        StringBuilder builder = new StringBuilder();
        List<JavaMethodDescriptor> methods = descriptor.getMethods();
        if (methods.isEmpty()) return SPACE;
        methods.forEach(m -> {
            m.getDescriptions().forEach(description -> builder.append(description).append(LINE));
            m.getAnnotations().forEach(annotation -> builder.append(annotation).append(LINE));
            builder.append(m.getModifier()).append(SPACE);
            m.getIdentifiers().forEach(identifier->builder.append(identifier).append(SPACE));
            builder.append(m.getReturnType()).append(SPACE);
            builder.append(m.getMethodName()).append(SPACE);
            builder.append("(");
            m.getParams().forEach((k,v) ->{
                String annotation = m.getParamAnnotations().get(k);
                if (annotation == null) annotation = BLANK;
                builder.append(annotation).append(SPACE);
                builder.append(v).append(SPACE).append(k).append(",");
            });
            int index = builder.lastIndexOf(",");
            if (index > -1){
                builder.delete(index,index+1);
            }
            builder.append(")");
            if (m.isAbstractMethod()){
                builder.append(";").append(LINE);
                return;
            }
            builder.append("{").append(LINE);
            m.getBodies().forEach(body -> builder.append(body).append(LINE));
            builder.append(LINE).append("}").append(LINE);
        });
        return builder.toString();
    }

    String toFileContent(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package").append(SPACE).append(descriptor.getPackageName()).append(END);
        stringBuilder.append(LINE);
        stringBuilder.append(getImports()).append(LINE);
        stringBuilder.append(getDescriptions());
        stringBuilder.append(getAnnotationNames());
        stringBuilder.append(descriptor.getModifier()).append(SPACE);
        stringBuilder.append(getClassType());
        stringBuilder.append("class").append(SPACE);
        stringBuilder.append(descriptor.getClassName()).append(SPACE);
        stringBuilder.append(getSuperClassNames()).append(SPACE);
        stringBuilder.append(getInterfaceNames()).append(SPACE);
        stringBuilder.append("{").append(LINE);
        stringBuilder.append(getFields()).append(LINE);
        stringBuilder.append(getMethods()).append(LINE);
        stringBuilder.append(LINE).append("}");
        return stringBuilder.toString();
    }

    public File toFile(){
        String className = this.descriptor.getClassName();
        File file = new File(className+".java");
        try(FileOutputStream fos = new FileOutputStream(file)) {
            String content = toFileContent();
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }



}
