/*
 *
 *  * Copyright 2019 http://www.hswebframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.hswebframework.web.service;

import org.hswebframework.ezorm.core.dsl.Query;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.dao.dynamic.QueryByEntityDao;

import java.util.List;

public interface DefaultDSLQueryService<E, PK>
        extends DefaultQueryByEntityService<E>, QueryService<E, PK> {

    @Override
    default List<E> select() {
        return createQuery().noPaging().list();
    }

    @Override
    default int count() {
        return createQuery().total();
    }

    /**
     * 创建本�?务的dsl查询�?作对象
     * �?�通过返回的Query对象进行dsl方�?�?作如:<br>
     * <code>
     * createQuery().where("id",1).single();
     * </code>
     *
     * @return {@link Query}
     * @see Query
     * @see org.hswebframework.ezorm.core.Conditional
     * @since 3.0
     */
    default Query<E, QueryParamEntity> createQuery() {
        Query<E, QueryParamEntity> query = Query.empty(new QueryParamEntity());
        query.setListExecutor(this::select);
        query.setTotalExecutor(this::count);
        query.setSingleExecutor(this::selectSingle);
        query.noPaging();
        return query;
    }

    /**
     * 指定一个dao映射接�?�,接�?�需继承{@link QueryByEntityDao}创建dsl数�?�查询对象<br>
     * �?�通过返回的Query对象进行dsl方�?�?作如:<br>
     * <code>
     * createQuery(userMapper).where("id",1).single();
     * </code>
     *
     * @param dao  dao接�?�
     * @param <PO> PO泛型
     * @return {@link Query}
     * @see Query
     * @see org.hswebframework.ezorm.core.Conditional
     * @since 3.0
     */
    static <PO> Query<PO, QueryParamEntity> createQuery(QueryByEntityDao<PO> dao) {
        Query<PO, QueryParamEntity> query = new Query<>(new QueryParamEntity());
        query.setListExecutor(dao::query);
        query.setTotalExecutor(dao::count);
        query.setSingleExecutor((param) -> {
            param.doPaging(0, 1);
            List<PO> list = dao.query(param);
            if (null == list || list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        });
        query.noPaging();
        return query;
    }
}
