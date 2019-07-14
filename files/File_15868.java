/*
 * Copyright 2019 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.hswebframework.web.controller;

import io.swagger.annotations.ApiOperation;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.annotation.Authorize;
import org.hswebframework.web.commons.entity.Entity;
import org.hswebframework.web.commons.entity.PagerResult;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.message.ResponseMessage;
import org.hswebframework.web.service.QueryByEntityService;
import org.hswebframework.web.service.QueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.hswebframework.web.controller.message.ResponseMessage.ok;

/**
 * 通用查询控制器。
 *
 * @param <E>  实体类型
 * @param <PK> 主键类型
 * @param <Q>  查询�?�件实体类型,默认�??供{@link QueryParamEntity}实现
 * @author zhouhao
 * @see QueryParamEntity
 * @see 3.0
 */
public interface QueryController<E, PK, Q extends Entity> {

    /**
     * 获�?�实现了{@link QueryByEntityService}和{@link QueryService}的�?务类
     *
     * @param <T> �?务类泛型
     * @return �?务类实例
     */
    @Authorize(ignore = true)
    <T extends QueryByEntityService<E> & QueryService<E, PK>> T getService();

    /**
     * 根�?��?�数动�?查询。<br>
     * �?�数泛型如果为QueryParamEntity,
     * 客户的�?�数 ?terms[0].column=name&terms[0].value=�?明
     * 则执行查询�?�件 where name = '�?明'
     * 具体使用方法�?�照 {@link QueryParamEntity}
     *
     * @param param �?�数
     * @return 查询结果
     */
    @Authorize(action = Permission.ACTION_QUERY)
    @GetMapping
    @ApiOperation(value = "根�?�动�?�?�件查询", responseReference = "get")
    default ResponseMessage<PagerResult<E>> list(Q param) {
        return ok(getService().selectPager(param));
    }

    @Authorize(action = Permission.ACTION_QUERY)
    @GetMapping("/no-paging")
    @ApiOperation(value = "�?分页动�?查询", responseReference = "get")
    default ResponseMessage<List<E>> listNoPaging(Q param) {
        if (param instanceof QueryParamEntity) {
            ((QueryParamEntity) param).setPaging(false);
        }
        return ok(getService().select(param));
    }

    @Authorize(action = Permission.ACTION_QUERY)
    @GetMapping("/count")
    @ApiOperation(value = "根�?�动�?�?�件统计", responseReference = "get")
    default ResponseMessage<Integer> count(Q param) {
        return ok(getService().count(param));
    }


    @Authorize(action = Permission.ACTION_GET)
    @GetMapping(path = "/{id:.+}")
    @ApiOperation("根�?�主键查询")
    default ResponseMessage<E> getByPrimaryKey(@PathVariable PK id) {
        return ok(assertNotNull(getService().selectByPk(id)));
    }

    @Authorize(action = Permission.ACTION_GET)
    @GetMapping(path = "/ids")
    @ApiOperation("根�?�主键查询多�?�记录")
    default ResponseMessage<List<E>> getByPrimaryKey(@RequestParam List<PK> ids) {
        return ok(assertNotNull(getService().selectByPk(ids)));
    }

    @Authorize(ignore = true)
    static <T> T assertNotNull(T obj) {
        if (null == obj) {
            throw new NotFoundException("{data_not_exist}");
        }
        return obj;
    }

}
