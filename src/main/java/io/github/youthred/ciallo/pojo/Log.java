package io.github.youthred.ciallo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
public class Log {

    private String logger;
    private boolean requestMapping = false;
}
