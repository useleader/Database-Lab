package util;/*
 * @author   yan
 * @time     2023/12/13
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import java.util.ArrayList;
import java.util.List;

public class StringExtractor {


    public List<String> extractNames(String namesString) {
        if (namesString == null) {
            return null;
        }
        List<String> namesList = new ArrayList<>();

        // 使用逗号分割字符串
        String[] namesArray = namesString.split(",");

        // 将分割后的每个元素添加到List中
        for (String name : namesArray) {
            namesList.add(name.trim()); // trim()用于去除前后的空格
        }

        return namesList;
    }
}
