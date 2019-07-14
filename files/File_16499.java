package org.hswebframework.web.organizational.authorization.simple.handler;

import org.hswebframework.ezorm.core.param.Term;
import org.hswebframework.utils.ClassUtils;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.access.DataAccessConfig;
import org.hswebframework.web.authorization.access.DataAccessHandler;
import org.hswebframework.web.authorization.access.ScopeDataAccessConfig;
import org.hswebframework.web.authorization.define.AuthorizingContext;
import org.hswebframework.web.authorization.exception.AccessDenyException;
import org.hswebframework.web.commons.entity.Entity;
import org.hswebframework.web.commons.entity.param.QueryParamEntity;
import org.hswebframework.web.controller.QueryController;
import org.hswebframework.web.organizational.authorization.PersonnelAuthentication;
import org.hswebframework.web.organizational.authorization.access.DataAccessType;
import org.hswebframework.web.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhouhao
 */
public abstract class AbstractScopeDataAccessHandler<E> implements DataAccessHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean defaultSuccessOnError = true;

    protected abstract Class<E> getEntityClass();

    protected abstract String getSupportScope();

    protected abstract String getOperationScope(E entity);

    protected abstract void applyScopeProperty(E entity, String value);

    protected abstract Term createQueryTerm(Set<String> scope, AuthorizingContext context);

    protected abstract Set<String> getTryOperationScope(String scopeType, PersonnelAuthentication authorization);

    @Override
    public boolean isSupport(DataAccessConfig access) {
        return access instanceof ScopeDataAccessConfig && access.getType().equals(getSupportScope());
    }

    @Override
    public boolean handle(DataAccessConfig access, AuthorizingContext context) {
        ScopeDataAccessConfig accessConfig = ((ScopeDataAccessConfig) access);
        if (!PersonnelAuthentication.current().isPresent()) {
            return false;
        }
        switch (accessConfig.getAction()) {
            case Permission.ACTION_QUERY:
                return handleQuery(accessConfig, context);
            case Permission.ACTION_GET:
            case Permission.ACTION_DELETE:
            case Permission.ACTION_UPDATE:
                return handleRW(accessConfig, context);
            case Permission.ACTION_ADD:
                return handleAdd(accessConfig, context);
            default:
                return false;
        }
    }

    protected PersonnelAuthentication getPersonnelAuthorization() {
        return PersonnelAuthentication.current()
                .orElseThrow(AccessDenyException::new);
    }

    protected boolean handleAdd(ScopeDataAccessConfig access, AuthorizingContext context) {
        Set<String> scopes = getTryOperationScope(access);
        String scope;
        if (scopes.isEmpty()) {
            return true;
        } else if (scopes.size() == 1) {
            scope = scopes.iterator().next();
        } else {
            scope = scopes.iterator().next();
            logger.warn("existing many scope :{} , try use config.", scope);
        }
        if (scope != null) {
            context.getParamContext().getParams().values().stream()
                    .filter(getEntityClass()::isInstance)
                    .map(getEntityClass()::cast)
                    .forEach(entity -> applyScopeProperty(entity, scope));
        } else {
            logger.warn("scope is null!");
        }
        return defaultSuccessOnError;
    }

    protected boolean handleRW(ScopeDataAccessConfig access, AuthorizingContext context) {
        //获�?�注解
        Object id = context.getParamContext()
                .<String>getParameter(
                        context.getDefinition()
                                .getDataAccessDefinition()
                                .getIdParameterName())
                .orElse(null);

        Object controller = context.getParamContext().getTarget();
        Set<String> ids = getTryOperationScope(access);
        String errorMsg;
        //通过QueryController获�?�QueryService
        //然�?�调用selectByPk 查询旧的数�?�,进行对比
        if (controller instanceof QueryController) {
            //判断是�?�满足�?�件(泛型为 getEntityClass)
            Class entityType = ClassUtils.getGenericType(controller.getClass(), 0);
            if (ClassUtils.instanceOf(entityType, getEntityClass())) {
                @SuppressWarnings("unchecked")
                QueryService<E, Object> queryService = ((QueryController<E, Object, Entity>) controller).getService();
                E oldData = queryService.selectByPk(id);
                return !(oldData != null && !ids.contains(getOperationScope(oldData)));
            } else {
                errorMsg = "GenericType[0] not instance of " + getEntityClass();
            }
        } else {
            errorMsg = "target not instance of QueryController";
        }
        logger.warn("do handle {} fail,because {}", access.getAction(), errorMsg);
        return defaultSuccessOnError;
    }

    protected Set<String> getTryOperationScope(ScopeDataAccessConfig access) {
        if (DataAccessType.SCOPE_TYPE_CUSTOM.equals(access.getScopeType())) {
            return access.getScope().stream().map(String::valueOf).collect(Collectors.toSet());
        }
        return getTryOperationScope(access.getScopeType(), getPersonnelAuthorization());
    }

    protected boolean handleQuery(ScopeDataAccessConfig access, AuthorizingContext context) {
        Entity entity = context.getParamContext().getParams()
                .values().stream()
                .filter(Entity.class::isInstance)
                .map(Entity.class::cast)
                .findAny().orElse(null);
        if (entity == null) {
            logger.warn("try validate query access, but query entity is null or not instance of org.hswebframework.web.commons.entity.Entity");
            return defaultSuccessOnError;
        }
        Set<String> scope = getTryOperationScope(access);
        if (scope.isEmpty()) {
            logger.warn("try validate query access,but config is empty!");
            return defaultSuccessOnError;
        }
        if (entity instanceof QueryParamEntity) {
            if (logger.isDebugEnabled()) {
                logger.debug("try rebuild query param ...");
            }

            //�?构查询�?�件
            //如: 旧的�?�件为 where name =? or name = ?
            //�?构�?�为: where org_id in(?,?) and (name = ? or name = ?)
            QueryParamEntity queryParamEntity = ((QueryParamEntity) entity);
            queryParamEntity.toNestQuery(query-> query.accept(createQueryTerm(scope, context)));

//            //�?构查询�?�件
//            //如: 旧的�?�件为 where name =? or name = ?
//            //�?构�?�为: where org_id in(?,?) and (name = ? or name = ?)
//            List<Term> oldParam = queryParamEntity.getTerms();
//            //清空旧的查询�?�件
//            queryParamEntity.setTerms(new ArrayList<>());
//            //添加一个查询�?�件
//            queryParamEntity
//                    .addTerm(createQueryTerm(scope, context))
//                    //客户端�??交的�?�数 作为嵌套�?�数
//                    .nest().setTerms(oldParam);
        } else {
            logger.warn("try validate query access,but entity not support, QueryParamEntity support now!");
        }
        return true;
    }

    protected boolean genericTypeInstanceOf(Class type, AuthorizingContext context) {
        Class entity = ClassUtils.getGenericType(context.getParamContext().getTarget().getClass());
        return null != entity && ClassUtils.instanceOf(entity, type);
    }
}
