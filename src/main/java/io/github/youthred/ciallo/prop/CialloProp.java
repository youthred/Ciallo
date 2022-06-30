package io.github.youthred.ciallo.prop;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "ciallo")
@Data
@Accessors(chain = true)
public class CialloProp {

    @NotNull
    private boolean enable = false;

    @NotNull
    private boolean saveLog = true;

    @NestedConfigurationProperty
    @Valid
    private DbProp dbProp;

    public DriverManagerDataSource getDataSource() {
        if (Objects.isNull(dbProp)) {
            return null;
        }
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(this.dbProp.getDriverClassName());
        ds.setUrl(this.dbProp.getUrl());
        ds.setUsername(this.dbProp.getUsername());
        ds.setPassword(this.dbProp.getPassword());
        return ds;
    }
}
