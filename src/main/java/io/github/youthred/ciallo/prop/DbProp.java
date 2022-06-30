package io.github.youthred.ciallo.prop;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
public class DbProp {

    @NotBlank
    private String driverClassName;
    @NotBlank
    private String url;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
