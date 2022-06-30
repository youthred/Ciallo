package io.github.youthred.ciallo.service;

import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.prop.CialloProp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbService {

    public static Db getDb() {
        return DbUtil.use(SpringUtil.getBean(CialloProp.class).getDataSource());
    }
}
