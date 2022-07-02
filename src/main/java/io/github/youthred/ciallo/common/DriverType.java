package io.github.youthred.ciallo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author https://github.com/youthred
 */
@Getter
@AllArgsConstructor
public enum DriverType {

    MYSQL("mysql", "limit 1", Constant.DEFAULT_TABLE_CREATE_SQL_MYSQL),
    ORACLE("oracle", "where rownum = 1", Constant.DEFAULT_TABLE_CREATE_SQL_ORACLE),
    ;

    final private String lowerCaseName;
    final private String creatIfNotExistCondition;
    final private List<String> createSql;

    public static String parseCreatIfNotExistCondition(String driverClassName) {
        return Arrays.stream(values())
                .filter(dt -> driverClassName.toLowerCase().contains(dt.lowerCaseName))
                .findFirst()
                .map(DriverType::getCreatIfNotExistCondition)
                .orElse(null);
    }

    public static List<String> parseCreateSql(String driverClassName) {
        return Arrays.stream(values())
                .filter(dt -> driverClassName.toLowerCase().contains(dt.lowerCaseName))
                .findFirst()
                .map(DriverType::getCreateSql)
                .orElse(null);
    }
}
