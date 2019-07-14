package org.hswebframework.web.organizational.authorization.access;

import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.access.ScopeDataAccessConfig;

/**
 * 控制类型
 *
 * @author zhouhao
 * @since 3.0
 */
public interface DataAccessType {
    /**
     * 控制地区
     */
    String DISTRICT_SCOPE = "DISTRICT";
    /**
     * 控制机构
     */
    String ORG_SCOPE = "ORG_SCOPE";
    /**
     * 控制部门
     */
    String DEPARTMENT_SCOPE = "DEPARTMENT_SCOPE";
    /**
     * 控制�?��?
     */
    String POSITION_SCOPE = "POSITION_SCOPE";
    /**
     * 控制人员
     */
    String PERSON_SCOPE = "PERSON_SCOPE";
    /**
     * 控制范围:仅�?本人
     */
    String SCOPE_TYPE_ONLY_SELF = "ONLY_SELF";
    /**
     * 控制范围:包�?��?级
     */
    String SCOPE_TYPE_CHILDREN = "CHILDREN";
    /**
     * 控制范围:自定义范围
     */
    String SCOPE_TYPE_CUSTOM = "CUSTOM_SCOPE";

    /* ===========行政区============*/
    static Permission.DataAccessPredicate<ScopeDataAccessConfig> districtScope(String action, String type) {
        return Permission.scope(action, ORG_SCOPE, type);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfDistrictScope(String action) {
        return districtScope(action, SCOPE_TYPE_ONLY_SELF);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> customDistrictScope(String action) {
        return districtScope(action, SCOPE_TYPE_CUSTOM);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenDistrictScope(String action) {
        return districtScope(action, SCOPE_TYPE_CHILDREN);
    }

    /* ===========机构============*/
    static Permission.DataAccessPredicate<ScopeDataAccessConfig> orgScope(String action, String type) {
        return Permission.scope(action, ORG_SCOPE, type);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfOrgScope(String action) {
        return orgScope(action, SCOPE_TYPE_ONLY_SELF);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> customOrgScope(String action) {
        return orgScope(action, SCOPE_TYPE_CUSTOM);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenOrgScope(String action) {
        return orgScope(action, SCOPE_TYPE_CHILDREN);
    }


    /* ===========部门===========*/

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> departmentScope(String action, String type) {
        return Permission.scope(action, DEPARTMENT_SCOPE, type);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfDepartmentScope(String action) {
        return departmentScope(action, SCOPE_TYPE_ONLY_SELF);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> customDepartmentScope(String action) {
        return departmentScope(action, SCOPE_TYPE_CUSTOM);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenDepartmentScope(String action) {
        return departmentScope(action, SCOPE_TYPE_CHILDREN);
    }

     /* ===========岗�?===========*/

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> positionScope(String action, String type) {
        return Permission.scope(action, POSITION_SCOPE, type);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfPositionScope(String action) {
        return positionScope(action, SCOPE_TYPE_ONLY_SELF);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> customPositionScope(String action) {
        return positionScope(action, SCOPE_TYPE_CUSTOM);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> childrenPositionScope(String action) {
        return positionScope(action, SCOPE_TYPE_CHILDREN);
    }

    /* ===========人员===========*/

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> selfScope(String action) {
        return personScope(action, SCOPE_TYPE_ONLY_SELF);
    }

    static Permission.DataAccessPredicate<ScopeDataAccessConfig> personScope(String action, String type) {
        return Permission.scope(action, PERSON_SCOPE, type);
    }
}
