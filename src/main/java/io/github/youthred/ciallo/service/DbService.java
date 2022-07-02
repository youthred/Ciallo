package io.github.youthred.ciallo.service;

import cn.hutool.db.Db;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.properties.CialloProperty;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    public static void check() throws Exception {
        Db.use().query("select id from " + SpringUtil.getBean(CialloProperty.class).getLogTableName());
    }
}
