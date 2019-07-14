package org.hswebframework.web.authorization.access;


import static org.hswebframework.web.authorization.access.DataAccessConfig.DefaultType.FIELD_SCOPE;

/**
 * 范围数�?��?��?控制�?置,控制�?个字段的值在范围内
 *
 * @author zhouhao
 * @see ScopeDataAccessConfig
 * @since 3.0
 */
public interface FieldScopeDataAccessConfig extends ScopeDataAccessConfig {
    /**
     * @return 字段信�?�
     */
    String getField();

    @Override
    default String getType() {
        return FIELD_SCOPE;
    }
}
