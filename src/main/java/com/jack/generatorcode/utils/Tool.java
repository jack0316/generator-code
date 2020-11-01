package com.jack.generatorcode.utils;

import org.springframework.util.StringUtils;

import java.text.MessageFormat;

public final class Tool {
    private Tool(){}



    public static String transHumpName(String name){
        if (StringUtils.isEmpty(name) || !name.contains("_")) return name;
        StringBuilder result = new StringBuilder();
        String[] camels = name.split("_");
        for (String camel :  camels) {
            if (camel.isEmpty()) {
                continue;
            }
            if (result.length() == 0) {
                result.append(camel.toLowerCase());
            } else {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String transClassName(String name){
        name = transHumpName(name);
        char[] chars = name.toCharArray();
        char firstChar = chars[0];
        String firstStr = Character.toString(firstChar).toUpperCase();
        chars[0] = firstStr.toCharArray()[0];
        return new String(chars);

    }

    public static String format(String patter,Object... params){
        if (params==null || params.length < 1) return patter;
        return MessageFormat.format(patter, params);
    }


}
