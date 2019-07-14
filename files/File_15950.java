package org.hswebframework.web.commons.entity.param;

import org.hswebframework.ezorm.core.dsl.Query;
import org.hswebframework.ezorm.core.dsl.Update;
import org.hswebframework.ezorm.core.param.UpdateParam;
import org.hswebframework.web.commons.entity.Entity;
import org.hswebframework.web.commons.entity.QueryEntity;

/**
 * 修改�?�数实体,使用<a href="https://github.com/hs-web/hsweb-easy-orm">easyorm</a>进行动�?�?�数构建
 *
 * @author zhouhao
 * @see UpdateParam
 * @see Entity
 * @since 3.0
 */
public class UpdateParamEntity<T> extends UpdateParam<T> implements QueryEntity {
    private static final long serialVersionUID = -4074863219482678510L;

    public UpdateParamEntity() {
    }

    public UpdateParamEntity(T data) {
        super(data);
    }

    /**
     * 创建一个无任何�?�件并指定数�?�的更新�?�数实体
     * 创建�?�需自行指定�?�件({@link UpdateParamEntity#where(String, Object)})
     * �?�则�?�能无法执行更新(dao实现应该�?止无�?�件的更新)
     *
     * @param data �?更新的数�?�
     * @param <T>  数�?�泛型
     * @return 更新�?�数实体
     */
    public static <T> UpdateParamEntity<T> build(T data) {
        return new UpdateParamEntity<>(data);
    }

    /**
     * 创建一个�?�个�?�件并指定数�?�的更新�?�数实体,�?�件默认为is:
     * <br>例如:<br>
     * <code>
     * // where id = #{id}
     * <br>
     * UpdateParamBean.build(data,"id",id);
     * </code>
     *
     * @param data  �?更新的数�?�
     * @param field �?�件�??
     * @param value �?�件值
     * @param <T>   数�?�泛型
     * @return 更新�?�数实体
     */
    public static <T> UpdateParamEntity<T> build(T data, String field, Object value) {
        return new UpdateParamEntity<>(data).where(field, value);
    }

    /**
     * @since 3.0.4
     */
    public static <T> Update<T, UpdateParamEntity<T>> newUpdate() {
        return new Update<>(new UpdateParamEntity<>());
    }

    @Override
    public String toString() {
        return toHttpQueryParamString();
    }

}
