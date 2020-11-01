package com.jack.generatorcode;

import com.jack.generatorcode.dataModel.database.BaseConfig;
import com.jack.generatorcode.dataModel.database.TableModel;
import com.jack.generatorcode.template.Template;
import com.jack.generatorcode.template.java.DaoTemplate;
import com.jack.generatorcode.template.java.EntityTemplate;
import com.jack.generatorcode.utils.base.MysqlBase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GeneratorCodeApplicationTests {

    @Test
    void contextLoads() throws IOException {

    }


    @Test
    public void baseTest() throws Exception {
        BaseConfig config = new BaseConfig();
        config.setDatabaseName("test01");
        config.setHost("localhost");
        config.setPort(3306);
        config.setTableName("users");
        config.setUser("root");
        config.setPassword("root");
        TableModel tableModel = MysqlBase.buildTableInfo(config);
        Template entityTemplate = new EntityTemplate(tableModel,"com.jack.code.entity");
        entityTemplate.createFile();
        Template daoTemplate = new DaoTemplate(tableModel,"com.jack.code.dao");
        daoTemplate.createFile();
    }

}
