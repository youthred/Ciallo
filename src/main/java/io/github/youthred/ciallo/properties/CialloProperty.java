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
     * 日志持久化库表名称
     */
    @NotBlank
    private String ciallogTableName = "T_CIALLOG";

    /**
     * 检查库表是否能正常使用
     */
    @NotNull
    private Boolean checkTable = true;

    /**
     * 使用Nginx代理时的真实 IP Header 名称
     */
    private String realIpHeader;

    /**
     * 数据源信息
     */
    @NestedConfigurationProperty
    @Valid
    private DsProperty ds;
}
