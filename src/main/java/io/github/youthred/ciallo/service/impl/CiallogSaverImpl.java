package io.github.youthred.ciallo.service.impl;

import io.github.youthred.ciallo.entity.Ciallog;
import io.github.youthred.ciallo.service.CiallogSaver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 默认日志持久化实现
 *
 * @author https://github.com/youthred
 */
@Slf4j
public class CiallogSaverImpl implements CiallogSaver {

    @Override
    public void save(Object o) {
        if (o instanceof Ciallog) {
            try {
                Ciallog ciallog = (Ciallog) o;
                log.info("[Ciallo] SAVED");
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        } else {
            log.warn("[Ciallo] You implemented and Spring componentized your own 'CiallogInterceptor' and returned an object of another type, the default 'CiallogSaver' cannot handle objects other than 'io.github.youthred.ciallo.entity.Ciallog', please implement and Spring componentized' CiallogSaver' to custom handle logs.");
        }
    }
}
