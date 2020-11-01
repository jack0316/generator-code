package com.jack.generatorcode.template.java;

import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.dataModel.destractor.java.JavaClassDescriptor;
import com.jack.generatorcode.utils.Tool;
import org.springframework.util.StringUtils;


public class DaoTemplate extends JavaTemplate {


    private final TableModel table;

    private final String packageName;

    private String className;

    public DaoTemplate(TableModel table, String packageName) {
        this.table = table;
        this.packageName = packageName;
        buildClassInfo();
        buildMethodInfo();
    }

    public void buildClassInfo() {
        className = Tool.transClassName(table.getTableName()) + "Dao";
        descriptor = new JavaClassDescriptor(className, packageName, "public");
        if (!StringUtils.isEmpty(table.getComment())) {
            descriptor.addDescription("/**").addDescription(table.getComment()).addDescription("*/");
        }
        descriptor.addImport("java.util.Date")
                .addImport("java.math.BigDecimal")
                .addImport("org.springframework.stereotype.Repository")
        ;
        descriptor.addAnnotation("@Repository");
        descriptor.setClassType("interface");
    }

    public void buildMethodInfo() {

    }


}
