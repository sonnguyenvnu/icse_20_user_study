package org.hswebframework.web.commons.entity;

import org.hswebframework.web.HttpParameterConverter;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author zhouhao
 * @since 3.0
 */
public interface QueryEntity extends Entity {
    /**
     *  转为http查询�?�数
     * @return
     */
    default String toHttpQueryParamString() {
        Map<String, String> result = new HttpParameterConverter(this).convert();
        StringJoiner joiner = new StringJoiner("&");
        result.forEach((key, value) -> joiner.add(key.concat("=").concat(value)));
        return joiner.toString();
    }
}
