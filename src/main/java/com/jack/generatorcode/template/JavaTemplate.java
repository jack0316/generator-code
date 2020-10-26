package com.jack.generatorcode.template;

import com.jack.generatorcode.template.destractor.JavaClassDescriptor;
import com.jack.generatorcode.template.destractor.JavaFiledDescriptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JavaTemplate extends TemplateAbstract {

    private final JavaClassDescriptor descriptor;
    private final String LINE = "\n";
    private final String SPACE = " ";
    private final String END = ";";

    public JavaTemplate(JavaClassDescriptor descriptor) {
        this.descriptor = descriptor;
    }


    public String getClassType() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptor.getClassType().forEach(type->stringBuilder.append(SPACE).append(type).append(SPACE));
        return stringBuilder.toString();
    }

    public String getDescriptions() {
        StringBuilder stringBuilder = new StringBuilder();
        this.descriptor.getDescriptions().forEach(description -> stringBuilder.append(description).append(LINE));
        return stringBuilder.toString();
    }

    public String getSuperClassNames() {
        List<String> superClassNames = this.descriptor.getSuperClassNames();
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
        List<String> interfaceNames = this.descriptor.getInterfaceNames();
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
        if (fields.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        fields.forEach(f -> {
            f.getFieldDescriptions().forEach(description -> stringBuilder.append(description).append(LINE));
            f.getAnnotations().forEach(annotation -> stringBuilder.append(annotation).append(LINE));
            stringBuilder.append(f.getModifier()).append(SPACE);
            stringBuilder.append(f.getFieldType()).append(SPACE);
            stringBuilder.append(f.getFieldName()).append(SPACE);
            stringBuilder.append(END).append(LINE);
        });
        return stringBuilder.toString();
    }

    String toFileContent(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package").append(SPACE).append(descriptor.getPackageName());
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
