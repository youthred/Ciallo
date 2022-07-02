package io.github.youthred.ciallo.properties;

import cn.hutool.setting.Setting;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
public class DsProperty {

    @NotBlank
    private String driverClassName;
    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    /**
     * 是否在日志中显示执行的SQL
     */
    public Boolean showSql = false;

    /**
     * 是否格式化显示SQL
     */
    public Boolean formatSql = false;

    /**
     * 是否显示SQL参数
     */
    public Boolean showParams = false;

    /**
     * 打印SQL的日志等级，默认debug
     */
    public String sqlLevel = "debug";

    public Setting buildSetting() {
        Setting setting = new Setting();
        setting.set("driver", this.driverClassName);
        setting.set("url", this.url);
        setting.set("username", this.username);
        setting.set("password", this.password);
        setting.set("showSql", String.valueOf(this.showSql));
        setting.set("formatSql", String.valueOf(this.formatSql));
        setting.set("showParams", String.valueOf(this.showParams));
        setting.set("sqlLevel", this.sqlLevel);
        return setting;
    }
}
