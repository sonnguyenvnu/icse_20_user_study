/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x7;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import x7.core.bean.BeanElement;
import x7.core.bean.Parsed;
import x7.core.bean.Parser;
import x7.core.bean.Transformed;
import x7.repository.BaseRepository;
import x7.repository.DataRepository;
import x7.repository.Repository;
import x7.repository.RepositoryBooter;
import x7.repository.schema.SchemaConfig;
import x7.repository.schema.SchemaTransformRepository;
import x7.repository.schema.customizer.SchemaTransformCustomizer;
import x7.repository.schema.customizer.SchemaTransformRepositoryBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RepositoryListener implements
        ApplicationListener<ApplicationStartedEvent> {


    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        Class<? extends BaseRepository> clzz = null;
        if (SchemaConfig.isSchemaTransformEnabled) {
            clzz = customizeSchemaTransform(applicationStartedEvent);
        }

        RepositoryBooter.onStarted();

        if (clzz != null){

            DataRepository dataRepository = (DataRepository) applicationStartedEvent.getApplicationContext().getBean(Repository.class);

            List list = list(dataRepository,clzz);//查出所有�?置
            if (!list.isEmpty()) {
                reparse(list);
            }
        }
    }

    private Class<? extends BaseRepository> customizeSchemaTransform(ApplicationStartedEvent applicationStartedEvent){
        Class<? extends BaseRepository> clzz = SchemaTransformRepository.class;
        SchemaTransformCustomizer customizer = null;
        try {
            customizer = applicationStartedEvent.getApplicationContext().getBean(SchemaTransformCustomizer.class);
        }catch (Exception e){
        }

        if (customizer != null) {
            SchemaTransformRepositoryBuilder builder = new SchemaTransformRepositoryBuilder();
            clzz = customizer.customize(builder);
        }

        SchemaTransformRepositoryBuilder.registry = null;

        return clzz;
    }


    private void reparse(List list) {

        Map<String,List<Transformed>> map = new HashMap<>();

        for (Object obj : list) {
            if (obj instanceof Transformed) {

                Transformed transformed = (Transformed) obj;
                String originTable = transformed.getOriginTable();
                List<Transformed> transformedList = map.get(originTable);
                if (transformedList == null){
                    transformedList = new ArrayList<>();
                    map.put(originTable,transformedList);
                }
                transformedList.add(transformed);
            }
        }

        for (Map.Entry<String,List<Transformed>> entry : map.entrySet()){
            String originTable = entry.getKey();

            Parsed parsed = Parser.getByTableName(originTable);
            if (parsed == null)
                continue;

            List<Transformed> transformedList = entry.getValue();
            for (Transformed transformed : transformedList) {
                parsed.setTableName(transformed.getTargetTable());
                parsed.setTransforemedAlia(transformed.getAlia());

                for (BeanElement be : parsed.getBeanElementList()){
                    if (be.getMapper().equals(transformed.getOriginColumn())){
                        be.mapper = transformed.getTargetColumn();
                        break;
                    }
                }
            }

            parsed.reset(parsed.getBeanElementList());

            Parsed parsedTransformed = Parser.getByTableName(parsed.getTableName());
            parsed.setParsedTransformed(parsedTransformed);

        }
    }

    private List list(DataRepository dataRepository,Class<? extends BaseRepository> clzz) {

        Type[] types = clzz.getGenericInterfaces();

        ParameterizedType parameterized = (ParameterizedType) types[0];
        Class clazz = (Class) parameterized.getActualTypeArguments()[0];

        return dataRepository.list(clazz);

    }

}
