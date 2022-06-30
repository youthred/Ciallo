package io.github.youthred.ciallo.prop;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "ciallo")
@Data
@Accessors(chain = true)
public class CialloProp {

    @NotNull
    private boolean enable = false;

    @NotNull
    private boolean saveLog = true;
}
