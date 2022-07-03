package io.github.youthred.ciallo.common;

import cn.hutool.core.exceptions.ValidateException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

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
    final private String createSql;

    public static String parseCreatIfNotExistCondition(String driverClassName) {
        return Arrays.stream(values())
                .filter(dt -> driverClassName.toLowerCase().contains(dt.lowerCaseName))
                .findFirst()
                .map(DriverType::getCreatIfNotExistCondition)
                .orElseThrow(() -> new ValidateException(Constant.LOG_NAME_HEAD + "unsupported driver '" + driverClassName + "'"));
    }

    public static String parseCreateSql(String driverClassName) {
        return Arrays.stream(values())
                .filter(dt -> driverClassName.toLowerCase().contains(dt.lowerCaseName))
                .findFirst()
                .map(DriverType::getCreateSql)
                .orElseThrow(() -> new ValidateException(Constant.LOG_NAME_HEAD + "unsupported driver '" + driverClassName + "'"));
    }
}
