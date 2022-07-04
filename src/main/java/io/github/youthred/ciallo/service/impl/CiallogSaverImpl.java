package io.github.youthred.ciallo.service.impl;

import cn.hutool.db.Db;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.common.Constant;
import io.github.youthred.ciallo.entity.Ciallog;
import io.github.youthred.ciallo.properties.CialloProperty;
import io.github.youthred.ciallo.service.CiallogSaver;
import io.github.youthred.ciallo.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.SQLException;

/**
 * 默认日志持久化实现
 *
 * @author https://github.com/youthred
 */
@Slf4j
public class CiallogSaverImpl implements CiallogSaver {

    static {
        if (SpringUtil.getBean(CialloProperty.class).getCheckTable()) {
            try {
                DbService.creatIfNotExist();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(Object o) {
        if (SpringUtil.getBean(CialloProperty.class).getSaveLog()) {
            if (o instanceof Ciallog) {
                try {
                    Ciallog ciallog = (Ciallog) o;
                    Db.use().insert(ciallog.toEntity());
                } catch (SQLException e) {
                    log.error(Constant.LOG_NAME_HEAD + "Ciallog save failed: {}", ExceptionUtils.getStackTrace(e));
                }
            } else {
                log.warn(Constant.LOG_NAME_HEAD + "You implemented and Spring componentized your own 'CiallogInterceptor' and returned an object of another type, the default 'CiallogSaver' cannot handle objects other than 'io.github.youthred.ciallo.entity.Ciallog', please implement and Spring componentized' CiallogSaver' to custom handle logs");
            }
        }
    }
}
