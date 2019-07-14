package org.hswebframework.web.commons.entity.param;

import org.hswebframework.ezorm.core.dsl.Delete;
import org.hswebframework.ezorm.core.dsl.Update;
import org.hswebframework.ezorm.core.param.Param;
import org.hswebframework.web.commons.entity.Entity;
import org.hswebframework.web.commons.entity.QueryEntity;

/**
 * 查询�?�数实体,使用<a href="https://github.com/hs-web/hsweb-easy-orm">easyorm</a>进行动�?查询�?�数构建<br>
 * �?�通过�?��?方法创建:<br>
 * {@link DeleteParamEntity#build()}<br>
 *
 * @author zhouhao
 * @see Param
 * @see Entity
 * @since 3.0
 */
public class DeleteParamEntity extends Param implements QueryEntity {
    private static final long serialVersionUID = 6120598637420234301L;

    /**
     * 创建一个无�?�件的删除�?�件实体
     * 创建�?�需自行指定�?�件({@link DeleteParamEntity#where(String, Object)})
     * �?�则�?�能无法执行更新(dao实现应该�?止无�?�件的删除)
     *
     * @return DeleteParamEntity
     */
    public static DeleteParamEntity build() {
        return new DeleteParamEntity();
    }

    /**
     * @since 3.0.4
     */
    public static Delete<DeleteParamEntity> newDelete() {
        return new Delete<>(new DeleteParamEntity());
    }

    @Override
    public String toString() {
        return toHttpQueryParamString();
    }

}
