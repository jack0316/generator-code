package com.jack.generatorcode;

import com.jack.generatorcode.template.JavaTemplate;
import com.jack.generatorcode.template.destractor.JavaClassDescriptor;
import com.jack.generatorcode.template.destractor.JavaFiledDescriptor;
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
        JavaClassDescriptor descriptor = new JavaClassDescriptor("User","com.jack.code","public");
        JavaFiledDescriptor filedDescriptor = new JavaFiledDescriptor("String","public","name");
        descriptor.addImport("java.util.Date")
                .addDescription("/** user */").addField(filedDescriptor);
        JavaTemplate template = new JavaTemplate(descriptor);
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
