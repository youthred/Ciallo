package io.github.youthred.ciallo.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "ciallo")
@Data
@Accessors(chain = true)
@Validated
public class CialloProperty {

    /**
     * 开启切片
     */
    @NotNull
    private Boolean enable = true;

    /**
     * 入库日志
     */
    @NotNull
    private Boolean saveLog = true;

    /**
     * 日志表名称
     */
    @NotBlank
    private String logTableName = "T_CIALLO_LOG";

    /**
     * 数据源信息
     */
    @NestedConfigurationProperty
    @Valid
    private DsProperty ds;
}
