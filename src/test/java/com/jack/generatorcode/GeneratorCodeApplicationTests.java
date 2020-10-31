package com.jack.generatorcode;

import com.jack.generatorcode.dataModel.database.ColumnModel;
import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.template.java.EntityTemplate;
import com.jack.generatorcode.template.java.JavaTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class GeneratorCodeApplicationTests {

    @Test
    void contextLoads() throws IOException {
        TableModel table = new TableModel();
        table.setComment("测试");
        table.setPrimaryKey("id");
        table.setTableName("user");
        table.setDatabaseName("test");
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName("id");
        columnModel.setComment("编号");
        columnModel.setJdbcType("integer");
        columnModel.setJavaType("Long");
        columnModel.setTypeLength(10);
        table.addColumn(columnModel);
        JavaTemplate template = new EntityTemplate(table,"com.jack.code");
        File dir = new File("./tmp/com/jack/code/");
        if (!dir.exists()) dir.mkdirs();
        File file = template.toFile();
        File tmpDir = new File(dir.getPath(),file.getName());
        System.out.println(tmpDir.getAbsolutePath());
        FileOutputStream fileOutputStream = new FileOutputStream(tmpDir);
        FileInputStream fis = new FileInputStream(file);
        fileOutputStream.write(fis.readAllBytes());
    }

}
