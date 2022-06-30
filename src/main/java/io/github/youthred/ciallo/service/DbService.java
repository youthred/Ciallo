package io.github.youthred.ciallo.service;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.prop.CialloProp;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DbService {

    public static Db getDb() {
        return DbUtil.use(SpringUtil.getBean(CialloProp.class).getDataSource());
    }

    public static void check() throws SQLException {
        getDb().query("select id from " + SpringUtil.getBean(CialloProp.class).getLogTableName());
    }
}
