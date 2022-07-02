package io.github.youthred.ciallo.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.common.Constant;
import io.github.youthred.ciallo.common.DriverType;
import io.github.youthred.ciallo.properties.CialloProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DbService {

    /**
     * 默认Saver表检查
     */
    public static void creatIfNotExist() {
        CialloProperty cp = SpringUtil.getBean(CialloProperty.class);
        Validate.isTrue(Objects.nonNull(cp.getDs()), Constant.LOG_NAME_HEAD + "Datasource properties can not be null");
        try {
            Db.use().query(StrUtil.format("select count(0) as cnt from {} {}", SpringUtil.getBean(CialloProperty.class).getLogTableName(), DriverType.parseCreatIfNotExistCondition(cp.getDs().getDriverClassName())));
        } catch (SQLException queryE) {
            log.error(Constant.LOG_NAME_HEAD + "Table check failed: {}", ExceptionUtils.getStackTrace(queryE));
            try {
                log.info(Constant.LOG_NAME_HEAD + "Try to create table '{}'", cp.getLogTableName());
                List<String> createSql = DriverType.parseCreateSql(cp.getDs().getDriverClassName());
                if (CollectionUtils.isNotEmpty(createSql)) {
                    for (String s : createSql) {
                        Db.use().execute(s);
                    }
                }
            } catch (SQLException createE) {
                log.error(Constant.LOG_NAME_HEAD + "Create table '{}' failed", cp.getLogTableName());
            }
        }
    }
}
