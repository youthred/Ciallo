package io.github.youthred.ciallo.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "ciallo")
@Data
@Accessors(chain = true)
@Validated
public class CialloProperty {

    /**
     * 启用Ciallo
     */
    @NotNull
    private Boolean enable = true;

    /**
     * 启用日志入库
     */
    @NotNull
    private Boolean saveLog = true;

    /**
     * 日志表名称
     */
    @NotBlank
    private String logTableName = "T_CIALLOG";

    /**
     * 数据源信息
     */
    @NestedConfigurationProperty
    @Valid
    private DsProperty ds;
}
