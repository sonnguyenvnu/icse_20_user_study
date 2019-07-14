package org.hswebframework.web.service.form.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hswebframework.ezorm.core.Delete;
import org.hswebframework.ezorm.core.Insert;
import org.hswebframework.ezorm.core.Update;
import org.hswebframework.ezorm.rdb.RDBDatabase;
import org.hswebframework.ezorm.rdb.RDBQuery;
import org.hswebframework.ezorm.rdb.RDBTable;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.hswebframework.ezorm.rdb.meta.RDBTableMetaData;
import org.hswebframework.web.BusinessException;
import org.hswebframework.web.NotFoundException;
import org.hswebframework.web.bean.FastBeanCopier;
import org.hswebframework.web.commons.entity.PagerResult;
import org.hswebframework.web.commons.entity.param.DeleteParamEntity;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.commons.entity.param.UpdateParamEntity;
import org.hswebframework.web.entity.form.DynamicFormEntity;
import org.hswebframework.web.service.form.DatabaseRepository;
import org.hswebframework.web.service.form.DynamicFormOperationService;
import org.hswebframework.web.service.form.DynamicFormService;
import org.hswebframework.web.service.form.FormDeployService;
import org.hswebframework.web.service.form.events.FormDataInsertBeforeEvent;
import org.hswebframework.web.service.form.events.FormDataUpdateBeforeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("dynamicFormOperationService")
@Transactional(rollbackFor = Throwable.class)
@Slf4j
public class SimpleDynamicFormOperationService implements DynamicFormOperationService {

    @Autowired
    private DynamicFormService dynamicFormService;

    @Autowired
    private DatabaseRepository databaseRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    protected <T> RDBTable<T> getTable(String formId) {
        DynamicFormEntity form = dynamicFormService.selectByPk(formId);
        if (null == form || Boolean.FALSE.equals(form.getDeployed())) {
            throw new NotFoundException("è¡¨å?•ä¸?å­˜åœ¨");
        }
        RDBDatabase database = StringUtils.isEmpty(form.getDataSourceId()) ?
                databaseRepository.getDefaultDatabase(form.getDatabaseName()) :
                databaseRepository.getDatabase(form.getDataSourceId(),form.getDatabaseName());
        return database.getTable(form.getDatabaseTableName());
    }

    protected RDBDatabase getDatabase(String formId) {
        DynamicFormEntity form = dynamicFormService.selectByPk(formId);
        if (null == form || Boolean.FALSE.equals(form.getDeployed())) {
            throw new NotFoundException("è¡¨å?•ä¸?å­˜åœ¨");
        }
        return StringUtils.isEmpty(form.getDataSourceId()) ?
                databaseRepository.getDefaultDatabase(form.getDatabaseName()) :
                databaseRepository.getDatabase(form.getDataSourceId(),form.getDatabaseName());
    }

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public <T> PagerResult<T> selectPager(String formId, QueryParamEntity paramEntity) {
        RDBTable<T> table = getTable(formId);
        RDBQuery<T> query = table.createQuery();
        int total = query.setParam(paramEntity).total();
        if (total == 0) {
            return PagerResult.empty();
        }
        paramEntity.rePaging(total);
        List<T> list = query.setParam(paramEntity).list(paramEntity.getPageIndex(), paramEntity.getPageSize());
        return PagerResult.of(total, list, paramEntity);
    }

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public <T> List<T> select(String formId, QueryParamEntity paramEntity) {
        RDBTable<T> table = getTable(formId);
        RDBQuery<T> query = table.createQuery();
        return query.setParam(paramEntity).list();
    }

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public <T> T selectSingle(String formId, QueryParamEntity paramEntity) {
        RDBTable<T> table = getTable(formId);
        RDBQuery<T> query = table.createQuery();
        return query.setParam(paramEntity).single();
    }

    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public int count(String formId, QueryParamEntity paramEntity) {
        RDBTable table = getTable(formId);
        RDBQuery query = table.createQuery();
        return query.setParam(paramEntity).total();
    }

    @Override
    @SneakyThrows
    public <T> int update(String formId, UpdateParamEntity<T> paramEntity) {
        if (Objects.requireNonNull(paramEntity).getTerms().isEmpty()) {
            throw new UnsupportedOperationException("ä¸?èƒ½æ‰§è¡Œæ— æ?¡ä»¶çš„æ›´æ–°æ“?ä½œ");
        }
        RDBTable<T> table = getTable(formId);
        Update<T> update = table.createUpdate();
        return update.setParam(paramEntity).exec();
    }

    @Override
    @SneakyThrows
    public <T> T insert(String formId, T entity) {
        RDBTable<T> table = getTable(formId);
        Insert<T> insert = table.createInsert();
        eventPublisher.publishEvent(new FormDataInsertBeforeEvent<>(formId, table, entity));
        insert.value(entity).exec();
        return entity;
    }

    private String getIdProperty(RDBTableMetaData tableMetaData) {
        return tableMetaData.getColumns()
                .stream()
                .filter(RDBColumnMetaData::isPrimaryKey)
                .findFirst()
                .map(RDBColumnMetaData::getAlias)
                .orElseThrow(() -> new BusinessException("è¡¨[" + tableMetaData.getComment() + "]æœªè®¾ç½®ä¸»é”®å­—æ®µ"));
    }

    @SneakyThrows
    protected <T> Object getExistingDataId(String formId, T data) {
        RDBTable<T> table = getTable(formId);
        String triggerName = "check-data-existing";

        boolean useTrigger = table.getMeta().triggerIsSupport(triggerName);
        String idProperty = getIdProperty(table.getMeta());
        //ä½¿ç”¨è§¦å?‘å™¨æ?¥åˆ¤æ–­æ˜¯å?¦å­˜åœ¨é‡?å¤?æ•°æ?®
        if (useTrigger) {
            Map<String, Object> triggerContext = new HashMap<>();
            triggerContext.put("table", table);
            triggerContext.put("database", getDatabase(formId));
            triggerContext.put("data", data);
            Object result = table.getMeta().on(triggerName, triggerContext);
            if (result instanceof String) {
                return result;
            }
            if (result instanceof Map) {
                Object id = ((Map) result).get(idProperty);
                if (id == null) {
                    log.error("table:[{}]è§¦å?‘å™¨è¿”å›žäº†æ•°æ?®:[{}],ä½†æ˜¯ä¸?åŒ…å?«ä¸»é”®å­—æ®µ:[{}]",
                            table.getMeta().getName(),
                            data,
                            idProperty);
                    throw new BusinessException("æ•°æ?®é”™è¯¯,è¯·è?”ç³»ç®¡ç?†å‘˜");
                }
                return id;
            }
        } else {
            Map<String, Object> mapData = FastBeanCopier.copy(data, new HashMap<>());
            Object id = mapData.get(idProperty);
            if (null == id) {
                return null;
            }
            Object existing = selectSingle(formId, QueryParamEntity.single(idProperty, id).includes(idProperty));
            if (null != existing) {
                mapData = FastBeanCopier.copy(data, new HashMap<>());
                return mapData.get(idProperty);
            }
        }

        return null;

    }

    @Override
    @SneakyThrows
    public <T> T saveOrUpdate(String formId, T data) {
        Objects.requireNonNull(formId, "è¡¨å?•IDä¸?èƒ½ä¸ºç©º");
        Object id = getExistingDataId(formId, data);
        if (null == id) {
            insert(formId, data);
        } else {
            updateById(formId, id, data);
        }
        return data;
    }

    @Override
    @SneakyThrows
    public int delete(String formId, DeleteParamEntity paramEntity) {
        if (Objects.requireNonNull(paramEntity).getTerms().isEmpty()) {
            throw new UnsupportedOperationException("ä¸?èƒ½æ‰§è¡Œæ— æ?¡ä»¶çš„åˆ é™¤æ“?ä½œ");
        }
        RDBTable table = getTable(formId);
        Delete delete = table.createDelete();
        return delete.setParam(paramEntity).exec();
    }

    @Override
    @SneakyThrows
    public int deleteById(String formId, Object id) {
        Objects.requireNonNull(id, "ä¸»é”®ä¸?èƒ½ä¸ºç©º");
        RDBTable table = getTable(formId);
        return table.createDelete()
                .where(getIdProperty(table.getMeta()), id)
                .exec();
    }

    @Override
    @SneakyThrows
    public <T> T selectById(String formId, Object id) {
        Objects.requireNonNull(id, "ä¸»é”®ä¸?èƒ½ä¸ºç©º");
        RDBTable<T> table = getTable(formId);
        return table.createQuery()
                .where(getIdProperty(table.getMeta()), id)
                .single();
    }

    @Override
    @SneakyThrows
    public <T> T updateById(String formId, Object id, T data) {
        Objects.requireNonNull(id, "ä¸»é”®ä¸?èƒ½ä¸ºç©º");
        RDBTable<T> table = getTable(formId);

        eventPublisher.publishEvent(new FormDataUpdateBeforeEvent<>(formId, table, data, id));

        table.createUpdate()
                .set(data)
                .where(getIdProperty(table.getMeta()), id)
                .exec();
        return data;
    }
}
