package io.github.youthred.ciallo.service;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.db.Db;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.common.Constant;
import io.github.youthred.ciallo.common.DriverType;
import io.github.youthred.ciallo.properties.CialloProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.SQLException;
import java.util.Objects;

@Slf4j
public class DbService {

    /**
     * 默认Saver表检查
     */
    public static void creatIfNotExist() {
        log.info(Constant.LOG_NAME_HEAD + "Check table exists...");
        CialloProperty cp = SpringUtil.getBean(CialloProperty.class);
        Validate.isTrue(Objects.nonNull(cp.getDs()), Constant.LOG_NAME_HEAD + "Datasource properties can not be null");
        try {
            String querySql = "select count(0) as cnt from " + cp.getCiallogTableName();
            String condition = DriverType.parseCreatIfNotExistCondition(cp.getDs().getDriverClassName());
            if (StringUtils.isNotBlank(condition)) {
                querySql = querySql + " " + condition;
            }
            Db.use().query(querySql);
        } catch (SQLException queryE) {
            log.error(Constant.LOG_NAME_HEAD + "Table check failed: {}", ExceptionUtil.getSimpleMessage(queryE));
            try {
                log.info(Constant.LOG_NAME_HEAD + "Try to create table '{}'", cp.getCiallogTableName());
                String createSql = DriverType.parseCreateSql(cp.getDs().getDriverClassName());
                if (StringUtils.isNotBlank(createSql)) {
                    Db.use().execute(createSql);
                }
                log.info(Constant.LOG_NAME_HEAD + "Create table '{}' success", cp.getCiallogTableName());
            } catch (SQLException createE) {
                log.error(Constant.LOG_NAME_HEAD + "Create table '{}' failed: {}", cp.getCiallogTableName(), ExceptionUtils.getStackTrace(createE));
            }
        }
    }
}
