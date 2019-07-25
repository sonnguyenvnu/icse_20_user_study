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
package x7.repository.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import x7.core.async.CasualWorker;
import x7.core.async.IAsyncTask;
import x7.core.bean.*;
import x7.core.bean.condition.InCondition;
import x7.core.bean.condition.ReduceCondition;
import x7.core.bean.condition.RefreshCondition;
import x7.core.repository.X;
import x7.core.util.StringUtil;
import x7.core.web.Direction;
import x7.core.web.Page;
import x7.repository.*;
import x7.repository.exception.PersistenceException;
import x7.repository.redis.JedisConnector_Persistence;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Biz Repository extends DefaultRepository
 *
 * @param <T>
 * @author Sim
 */
public abstract class DefaultRepository<T> implements BaseRepository<T> {

    private final static Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    public final static String ID_MAP_KEY = "ID_MAP_KEY";

    private Class<T> clz;

    @Override
    public Class<T> getClz() {
        return clz;
    }

    public void setClz(Class<T> clz) {
        this.clz = clz;
    }
    
    private DataRepository dataRepository;
    public void setDataRepository(DataRepository repository) {
        this.dataRepository = repository;
    }

    public DefaultRepository(){
        parse();
    }

    private void parse(){
        Type genType = getClass().getGenericSuperclass();

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (! (params[0] instanceof Class)){
            return;
        }

        this.clz = (Class) params[0];

        hook();

    }

    protected void hook() {
        if (!EntityHolder.listAll().contains(this.clz)) {
            EntityHolder.listAll().add(this.clz);
        }
        if (!HealthChecker.getRepositoryList().contains(this)) {
            HealthChecker.getRepositoryList().add(this);
        }
    }


    @Override
    public long createId() {

        final String name = clz.getName();
        final long id = JedisConnector_Persistence.getInstance().hincrBy(ID_MAP_KEY, name, 1);

        if (id == 0) {
            throw new PersistenceException("UNEXPECTED EXCEPTION WHILE CREATING ID");
        }

        CasualWorker.accept(new IAsyncTask() {

            @Override
            public void execute() throws Exception {
                IdGenerator generator = new IdGenerator();
                generator.setClzName(name);
                generator.setMaxId(id);
                StringBuilder sb = new StringBuilder();
                sb.append("update idGenerator set maxId = ").append(id).append(" where clzName = '").append(name)
                        .append("' and ").append(id).append(" > maxId ;");//sss

                try {
                    Parsed parsed = Parser.get(IdGenerator.class);
                    String sql = sb.toString();
                    ManuRepository.execute(generator, sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

        return id;
    }

    @Override
    public boolean createBatch(List<T> objList) {
        return dataRepository.createBatch(objList);
    }

    @Override
    public long create(T obj) {
        /*
         * FIXME
         */
        logger.info("BaesRepository.create: " + obj);

        long id = dataRepository.create(obj);

        return id;

    }

    @Override
    public boolean refresh(T obj) {

        Parsed parsed = Parser.get(this.clz);
        Field keyField = parsed.getKeyField(X.KEY_ONE);

        if (Objects.isNull(keyField))
            throw new RuntimeException("No PrimaryKey, UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");

        keyField.setAccessible(true);
        try {
            Object value = keyField.get(obj);
            if (Objects.isNull(value) || value.toString().equals("0"))
                throw new RuntimeException("UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("refresh safe, get keyOne exception");
        }


        return dataRepository.refresh(obj);
    }

    @Override
    public boolean refresh(RefreshCondition<T> refreshCondition) {

        refreshCondition.setClz(this.clz);
        Parsed parsed = Parser.get(this.clz);
        Field keyField = parsed.getKeyField(X.KEY_ONE);
        if (Objects.isNull(keyField))
            throw new PersistenceException("No PrimaryKey, UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");


        T obj = refreshCondition.getObj();
        CriteriaCondition criteriaCondition = refreshCondition.getCondition();

        boolean unSafe = false;//Safe

        if (Objects.nonNull(obj)) {
            keyField.setAccessible(true);
            try {
                Object value = keyField.get(obj);
                if (Objects.isNull(value)) {
                    unSafe = true;//UnSafe
                } else if (value.toString().equals("0")) {
                    unSafe = true;//UnSafe
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("refresh safe, get keyOne exception");
            }

        } else {
            unSafe = true;//UnSafe
        }

        if (unSafe) {
            String key = parsed.getKey(X.KEY_ONE);
            for (Criteria.X x : criteriaCondition.getListX()) {
                if (key.equals(x.getKey())) {
                    Object value = x.getValue();
                    if (Objects.nonNull(value) && !value.toString().equals("0")) {
                        unSafe = false;//Safe
                    }
                }
            }
        }

        if (unSafe)
            throw new PersistenceException("UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");

        return dataRepository.refresh(refreshCondition);
    }

    @Override
    public boolean refreshUnSafe(RefreshCondition<T> refreshCondition) {
        refreshCondition.setClz(this.clz);
        return dataRepository.refresh(refreshCondition);
    }


    @Override
    public void remove(T obj) {
        dataRepository.remove(obj);
    }

    @Override
    public T get(long idOne) {

        return dataRepository.get(clz, idOne);
    }

    @Override
    public List<T> list() {

        return dataRepository.list(clz);
    }

    @Override
    public List<T> list(T conditionObj) {

        if (conditionObj instanceof Criteria.ResultMappedCriteria) {
            throw new RuntimeException(
                    "Exception supported, no page not to invoke dataRepository.list(criteriaJoinalbe);");
        }

        return dataRepository.list(conditionObj);
    }

    @Override
    public T getOne(T conditionObj, String orderBy, Direction sc) {

        return dataRepository.getOne(conditionObj, orderBy, sc);
    }

    @Override
    public T getOne(T conditionObj) {

        return dataRepository.getOne(conditionObj);

    }

    @Override
    public void refreshCache() {
        dataRepository.refreshCache(clz);
    }

    @Override
    public Object reduce(ReduceCondition reduceCondition) {
        reduceCondition.setClz(this.clz);
        return dataRepository.reduce(reduceCondition);
    }


    @Override
    public List<T> in(InCondition inCondition) {
        inCondition.setClz(this.clz);
        return dataRepository.in(inCondition);
    }


    @Override
    public Page<T> find(Criteria criteria) {

        if (criteria instanceof Criteria.ResultMappedCriteria)
            throw new RuntimeException("Codeing Exception: maybe {Criteria.ResultMappedCriteria criteria = builder.get();} instead of {Criteria criteria = builder.get();}");
        return dataRepository.find(criteria);
    }


    @Override
    public Page<Map<String, Object>> find(Criteria.ResultMappedCriteria criteria) {

        return dataRepository.find(criteria);
    }


    @Override
    public List<Map<String, Object>> list(Criteria.ResultMappedCriteria resultMapped) {
        return dataRepository.list(resultMapped);
    }

    @Override
    public List<T> list(Criteria criteria) {

        if (criteria instanceof Criteria.ResultMappedCriteria)
            throw new RuntimeException("Codeing Exception: maybe {Criteria.ResultMappedCriteria criteria = builder.get();} instead of {Criteria criteria = builder.get();}");

        return dataRepository.list(criteria);
    }


    @Override
    public <WITH> List<DomainObject<T, WITH>> listDomainObject(Criteria.DomainObjectCriteria domainObjectCriteria) {

        if (StringUtil.isNullOrEmpty(domainObjectCriteria.getMainPropperty()))
            throw new RuntimeException("DefaultRepository.listDomainObject(domainObjectCriteria), domainObjectCriteria.getMainPropperty()is null");

        if (domainObjectCriteria.getRelativeClz() == null){

            if (domainObjectCriteria.getKnownMainIdList() == null || domainObjectCriteria.getKnownMainIdList().isEmpty()){
                return DomainObjectRepositoy.listDomainObject_NonRelative(domainObjectCriteria);
            }else{
                return DomainObjectRepositoy.listDomainObject_Known_NonRelative(domainObjectCriteria);
            }

        }else{
            if (domainObjectCriteria.getKnownMainIdList() == null || domainObjectCriteria.getKnownMainIdList().isEmpty()){
                return DomainObjectRepositoy.listDomainObject_HasRelative(domainObjectCriteria);
            }else{
                return DomainObjectRepositoy.listDomainObject_Known_HasRelative(domainObjectCriteria);
            }
        }

    }


}
