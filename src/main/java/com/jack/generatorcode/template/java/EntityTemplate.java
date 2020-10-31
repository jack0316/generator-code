package com.jack.generatorcode.template.java;

import com.jack.generatorcode.dataModel.database.ColumnModel;
import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.dataModel.destractor.java.JavaClassDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaFiledDescriptor;
import com.jack.generatorcode.dataModel.destractor.java.JavaMethodDescriptor;

import java.util.List;

public class EntityTemplate  extends JavaTemplate{

    private final TableModel table ;

    private final String packageName;

    public EntityTemplate(TableModel table, String packageName) {
        this.table = table;
        this.packageName = packageName;
       buildClassInfo();
       buildFieldInfo();
       buildMethodInfo();
    }

    public void buildClassInfo(){
        descriptor = new JavaClassDescriptor(table.getTableName(),packageName,"public");
        descriptor.addDescription("/**").addDescription(table.getComment()).addDescription("*/");
        descriptor.addImport("java.util.Date").addImport("java.math.BigDecimal");
    }

    public void buildFieldInfo(){
        List<ColumnModel> columns = table.getColumns();
        columns.forEach(column -> {
            JavaFiledDescriptor filed = new JavaFiledDescriptor("public",column.getJavaType(),column.getColumnName());
            filed.addDescription("/**"+column.getComment()+"*/");
            descriptor.addField(filed);
        });
    }

    public void buildMethodInfo(){
        List<JavaFiledDescriptor> fields = descriptor.getFields();
        fields.forEach(f -> {
            JavaMethodDescriptor method = new JavaMethodDescriptor("public",f.getFieldType(),"get"+f.getFieldName(),false);
            method.addBody("return "+f.getFieldName());
            descriptor.addMethod(method);
        });
    }


}
