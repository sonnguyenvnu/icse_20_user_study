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

package org.hswebframework.web.dao.mybatis;

import org.hswebframework.web.datasource.DataSourceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * mybatis�?置,继承官方�?置类,增加一些属性以拓展更多功能
 * <ul>
 * <li>是�?��?�用动�?数�?��?{@link this#dynamicDatasource}</li>
 * <li>�?�设置�?加载的�?置{@link this#mapperLocationExcludes}</li>
 * </ul>
 *
 * @author zhouhao
 * @see org.mybatis.spring.boot.autoconfigure.MybatisProperties
 * @since 3.0
 */
public class MybatisProperties extends org.mybatis.spring.boot.autoconfigure.MybatisProperties {
    /**
     * 默认支�?的hsweb mapper
     */
    private static final String   defaultMapperLocation  = "classpath*:org/hswebframework/web/dao/mybatis/mappers/**/*.xml";
    /**
     * 是�?��?�用动�?数�?��?
     * �?�用�?�调用{@link DataSourceHolder#switcher()},mybatis也会进行数�?��?切�?�
     *
     * @see DataSourceHolder#switcher()
     */
    private              boolean  dynamicDatasource      = false;
    /**
     * 排除加载的mapper.xml
     * 想自定义mapper并覆盖原始mapper的场景下，通过设置此属性�?�排除�?置文件。
     * 排除使用{@link Resource#getURL()#toString()}进行对比
     */
    private              String[] mapperLocationExcludes = null;
    /**
     * 使用jpa注解�?�解�?表结构，动�?生�?查询�?�件
     */
    private              boolean  useJpa                 = true;

    private List<MybatisMapperCustomizer> mybatisMappers;

    @Autowired(required = false)
    public void setMybatisMappers(List<MybatisMapperCustomizer> mybatisMappers) {
        this.mybatisMappers = mybatisMappers;
    }

    public String[] getMapperLocationExcludes() {
        return mapperLocationExcludes;
    }

    public void setMapperLocationExcludes(String[] mapperLocationExcludes) {
        this.mapperLocationExcludes = mapperLocationExcludes;
    }

    public boolean isDynamicDatasource() {
        return dynamicDatasource;
    }

    public void setDynamicDatasource(boolean dynamicDatasource) {
        this.dynamicDatasource = dynamicDatasource;
    }

    public void setUseJpa(boolean useJpa) {
        this.useJpa = useJpa;
    }

    public boolean isUseJpa() {
        return useJpa;
    }

    @Override
    public Resource[] resolveMapperLocations() {
        Map<String, Resource> resources = new HashMap<>();
        Set<String> locations;

        if (this.getMapperLocations() == null) {
            locations = new HashSet<>();
        } else {
            locations = Arrays.stream(getMapperLocations()).collect(Collectors.toSet());
        }

        locations.add(defaultMapperLocation);

        if (mybatisMappers != null) {
            mybatisMappers.stream()
                    .map(MybatisMapperCustomizer::getIncludes)
                    .flatMap(Arrays::stream)
                    .forEach(locations::add);
        }

        for (String mapperLocation : locations) {
            Resource[] mappers;
            try {
                mappers = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
                for (Resource mapper : mappers) {
                    resources.put(mapper.getURL().toString(), mapper);
                }
            } catch (IOException e) {
            }
        }
        Set<String> excludes = new HashSet<>();
        if (mybatisMappers != null) {
            mybatisMappers.stream()
                    .map(MybatisMapperCustomizer::getExcludes)
                    .flatMap(Arrays::stream)
                    .forEach(excludes::add);
        }
        if (mapperLocationExcludes != null && mapperLocationExcludes.length > 0) {
            for (String exclude : mapperLocationExcludes) {
                excludes.add(exclude);
            }
        }
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //排除�?需�?的�?置
        for (String mapperLocationExclude : excludes) {
            try {
                Resource[] excludesMappers = resourcePatternResolver.getResources(mapperLocationExclude);
                for (Resource excludesMapper : excludesMappers) {
                    resources.remove(excludesMapper.getURL().toString());
                }
            } catch (IOException e) {
            }
        }
        Resource[] mapperLocations = new Resource[resources.size()];
        mapperLocations = resources.values().toArray(mapperLocations);
        return mapperLocations;
    }

}
