package com.jack.generatorcode.template.java;

import com.jack.generatorcode.dataModel.database.ColumnModel;
import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.dataModel.destractor.java.JavaClassDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaFiledDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaMethodDescriptor;
import com.jack.generatorcode.utils.Tool;
import org.springframework.util.StringUtils;

import java.util.List;

public class EntityTemplate extends JavaTemplate {

    private final TableModel table;

    private final String packageName;

    private String className;

    public EntityTemplate(TableModel table, String packageName) {
        this.table = table;
        this.packageName = packageName;
        buildClassInfo();
        buildFieldInfo();
        buildMethodInfo();
    }

    public void buildClassInfo() {
        className = Tool.transClassName(table.getTableName()) + "Entity";
        descriptor = new JavaClassDescriptor(className, packageName, "public");
        if (!StringUtils.isEmpty(table.getComment())) {
            descriptor.addDescription("/**").addDescription(table.getComment()).addDescription("*/");
        }
        descriptor.addImport("java.util.Date").addImport("java.math.BigDecimal");
        descriptor.setClassType("class");
    }

    public void buildFieldInfo() {
        List<ColumnModel> columns = table.getColumns();
        columns.forEach(column -> {
            JavaFiledDescriptor filed = new JavaFiledDescriptor("public", column.getJavaType(), Tool.transHumpName(column.getColumnName()));
            if (!StringUtils.isEmpty(column.getComment())) {
                filed.addDescription("/**" + column.getComment() + "*/");
            }
            descriptor.addField(filed);
        });
    }

    public void buildMethodInfo() {
        List<JavaFiledDescriptor> fields = descriptor.getFields();
        fields.forEach(f -> {
            JavaMethodDescriptor getMethod = new JavaMethodDescriptor("public", f.getFieldType(), "get" + Tool.transClassName(f.getFieldName()), false);
            getMethod.addBody("return " + f.getFieldName() + END);
            getMethod.setDescriptions(f.getFieldDescriptions());
            descriptor.addMethod(getMethod);
            JavaMethodDescriptor setMethod = new JavaMethodDescriptor("public", f.getFieldType(), "set" + Tool.transClassName(f.getFieldName()), false);
            setMethod.addBody("this." + f.getFieldName() + "=" + f.getFieldName() + END);
            setMethod.addParams(f.getFieldName(), f.getFieldType(), BLANK);
            setMethod.setDescriptions(f.getFieldDescriptions());
            descriptor.addMethod(setMethod);
        });
    }


}
