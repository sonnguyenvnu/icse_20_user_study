/*
 *  Copyright 2019 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.hswebframework.web.service.authorization.simple;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.web.authorization.Authentication;
import org.hswebframework.web.authorization.AuthenticationInitializeService;
import org.hswebframework.web.authorization.Permission;
import org.hswebframework.web.authorization.access.DataAccessConfig;
import org.hswebframework.web.authorization.simple.SimpleAuthentication;
import org.hswebframework.web.authorization.simple.SimplePermission;
import org.hswebframework.web.authorization.simple.SimpleRole;
import org.hswebframework.web.authorization.simple.SimpleUser;
import org.hswebframework.web.commons.entity.DataStatus;
import org.hswebframework.web.commons.entity.TreeSupportEntity;
import org.hswebframework.web.dao.authorization.AuthorizationSettingDao;
import org.hswebframework.web.dao.authorization.AuthorizationSettingDetailDao;
import org.hswebframework.web.entity.authorization.*;
import org.hswebframework.web.id.IDGenerator;
import org.hswebframework.web.service.DefaultDSLDeleteService;
import org.hswebframework.web.service.DefaultDSLQueryService;
import org.hswebframework.web.service.GenericEntityService;
import org.hswebframework.web.service.authorization.*;
import org.hswebframework.web.service.authorization.AuthorizationSettingTypeSupplier.SettingInfo;
import org.hswebframework.web.service.authorization.events.ClearUserAuthorizationCacheEvent;
import org.hswebframework.web.validator.group.CreateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.hswebframework.web.commons.entity.DataStatus.STATUS_ENABLED;
import static org.hswebframework.web.entity.authorization.AuthorizationSettingDetailEntity.*;
import static org.hswebframework.web.entity.authorization.AuthorizationSettingEntity.settingFor;
import static org.hswebframework.web.entity.authorization.AuthorizationSettingEntity.type;
import static org.hswebframework.web.service.authorization.simple.CacheConstants.USER_AUTH_CACHE_NAME;
import static org.hswebframework.web.service.authorization.simple.CacheConstants.USER_MENU_CACHE_NAME;

/**
 * é»˜è®¤çš„æœ?åŠ¡å®žçŽ°
 *
 * @author hsweb-generator-online
 */
@Service("authorizationSettingService")
public class SimpleAuthorizationSettingService extends GenericEntityService<AuthorizationSettingEntity, String>
        implements AuthorizationSettingService, AuthenticationInitializeService, UserMenuManagerService {

    private AuthorizationSettingDao authorizationSettingDao;

    private AuthorizationSettingDetailDao authorizationSettingDetailDao;

    private AuthorizationSettingMenuService authorizationSettingMenuService;

    private MenuService menuService;

    private UserService userService;

    private PermissionService permissionService;

    private List<AuthorizationSettingTypeSupplier> authorizationSettingTypeSuppliers;

    private DataAccessFactory dataAccessFactory;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public AuthorizationSettingDao getDao() {
        return authorizationSettingDao;
    }

    @Override
    public AuthorizationSettingEntity select(String type, String settingFor) {
        tryValidateProperty(type != null, AuthorizationSettingEntity.type, "{can not be null}");
        tryValidateProperty(settingFor != null, AuthorizationSettingEntity.settingFor, "{can not be null}");
        return createQuery().where(AuthorizationSettingEntity.type, type)
                .and(AuthorizationSettingEntity.settingFor, settingFor)
                .single();
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.USER_AUTH_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public String saveOrUpdate(AuthorizationSettingEntity entity) {
        AuthorizationSettingEntity old = select(entity.getType(), entity.getSettingFor());
        if (old != null) {
            updateByPk(old.getId(), entity);
            return old.getId();
        }
        return insert(entity);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.USER_AUTH_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public String insert(AuthorizationSettingEntity entity) {
        tryValidateProperty(select(entity.getType(), entity.getSettingFor()) == null, AuthorizationSettingEntity.settingFor, "å­˜åœ¨ç›¸å?Œçš„é…?ç½®!");
        entity.setStatus(STATUS_ENABLED);
        String id = super.insert(entity);
        if (entity.getMenus() != null) {
            TreeSupportEntity.forEach(entity.getMenus(), menu -> {
                menu.setStatus(STATUS_ENABLED);
                menu.setSettingId(id);
            });
            authorizationSettingMenuService.insertBatch(entity.getMenus());
        }
        if (entity.getDetails() != null) {
            for (AuthorizationSettingDetailEntity detail : entity.getDetails()) {
                detail.setId(getIDGenerator().generate());
                detail.setSettingId(id);
                detail.setStatus(STATUS_ENABLED);
                tryValidate(detail, CreateGroup.class);
                authorizationSettingDetailDao.insert(detail);
            }
        }
        return id;
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.USER_AUTH_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public int updateByPk(String id, AuthorizationSettingEntity entity) {
        int size = super.updateByPk(id, entity);
        if (entity.getMenus() != null) {
            authorizationSettingMenuService.deleteBySettingId(id);
            TreeSupportEntity.forEach(entity.getMenus(), menu -> {
                menu.setStatus(STATUS_ENABLED);
                menu.setSettingId(id);
            });
            authorizationSettingMenuService.insertBatch(entity.getMenus());
        }
        if (entity.getDetails() != null) {
            DefaultDSLDeleteService
                    .createDelete(authorizationSettingDetailDao)
                    .where(settingId, id)
                    .exec();
            for (AuthorizationSettingDetailEntity detail : entity.getDetails()) {
                detail.setId(getIDGenerator().generate());
                detail.setSettingId(id);
                detail.setStatus(STATUS_ENABLED);
                tryValidate(detail, CreateGroup.class);
                authorizationSettingDetailDao.insert(detail);
            }
        }
        return size;
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.USER_AUTH_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public AuthorizationSettingEntity deleteByPk(String id) {
        Objects.requireNonNull(id, "id can not be null");
        authorizationSettingMenuService.deleteBySettingId(id);
        DefaultDSLDeleteService.createDelete(authorizationSettingDetailDao)
                .where(AuthorizationSettingDetailEntity.settingId, id).exec();
        return super.deleteByPk(id);
    }


    private List<AuthorizationSettingEntity> getUserSetting(String userId) {
        Map<String, List<SettingInfo>> settingInfo =
                authorizationSettingTypeSuppliers.stream()
                        .map(supplier -> supplier.get(userId))
                        .flatMap(Set::stream)
                        .collect(Collectors.groupingBy(SettingInfo::getType));
        Stream<Map.Entry<String, List<SettingInfo>>> settingInfoStream = settingInfo.entrySet().stream();
        //å¤§äºŽ1 ä½¿ç”¨å¹¶è¡Œå¤„ç?†
        if (settingInfo.size() > 1) {
            settingInfoStream = settingInfoStream.parallel();
        }
        return settingInfoStream
                .map(entry ->
                        createQuery()
                                // where type = ? and setting_for in (?,?,?....)
                                .where(type, entry.getKey())
                                .and()
                                .in(settingFor, entry.getValue().stream().map(SettingInfo::getSettingFor).collect(Collectors.toList()))
                                .listNoPaging())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = USER_MENU_CACHE_NAME, key = "'user-menu-list:'+#userId")
    public List<UserMenuEntity> getUserMenuAsList(String userId) {
        if (null == userId) {
            return new java.util.ArrayList<>();
        }
        UserEntity userEntity = userService.selectByPk(userId);
        if (userEntity == null) {
            return new java.util.ArrayList<>();
        }
        List<AuthorizationSettingEntity> entities = getUserSetting(userId);
        if (entities.isEmpty()) {
            return new java.util.ArrayList<>();
        }
        //ç”¨æˆ·æŒ?æœ‰çš„æ?ƒé™?è®¾ç½®idé›†å?ˆ
        List<String> settingIdList = entities.stream()
                .map(AuthorizationSettingEntity::getId)
                .collect(Collectors.toList());
        //èŽ·å?–å…¨éƒ¨è®¾ç½®çš„è?œå?•
        List<AuthorizationSettingMenuEntity> menuEntities = authorizationSettingMenuService
                .selectBySettingId(settingIdList);
        //å¾—åˆ°è?œå?•id
        List<String> menuIdList = menuEntities.stream()
                .map(AuthorizationSettingMenuEntity::getMenuId)
                .distinct()
                .collect(Collectors.toList());
        if (menuIdList.isEmpty()) {
            return new ArrayList<>();
        }
        //èŽ·å?–å…¨éƒ¨è?œå?•,å¹¶åˆ›å»ºç¼“å­˜å¤‡ç”¨
        Map<String, MenuEntity> menuCache = menuService
                .selectByPk(menuIdList)
                .stream()
                .collect(Collectors.toMap(MenuEntity::getId, Function.identity()));

        //æ ¹æ?®é…?ç½®,é‡?æ–°æž„é€ è?œå?•ç»“æž„
        List<UserMenuEntity> reBuildMenu = new LinkedList<>();
        for (MenuEntity menuEntity : menuCache.values()) {
            UserMenuEntity menu = entityFactory.newInstance(UserMenuEntity.class, menuEntity);
            menu.setSortIndex(menuEntity.getSortIndex());
            menu.setLevel(menuEntity.getLevel());
            menu.setId(menuEntity.getId());
            menu.setParentId(menuEntity.getParentId());
            menu.setMenuId(menuEntity.getId());
            reBuildMenu.add(menu);
        }

//        for (AuthorizationSettingMenuEntity entity : menuEntities) {
//            MenuEntity cache = menuCache.get(entity.getMenuId());
//            if (null != cache && DataStatus.STATUS_ENABLED.equals(cache.getStatus())) {
//                UserMenuEntity menu = entityFactory.newInstance(UserMenuEntity.class, cache);
//                menu.setSortIndex(entity.getSortIndex());
//                menu.setLevel(entity.getLevel());
//                menu.setId(entity.getId());
//                menu.setParentId(entity.getParentId());
//                menu.setMenuId(cache.getId());
//                reBuildMenu.add(menu);
//            }
//        }
        Collections.sort(reBuildMenu);
        return reBuildMenu;
    }

    @Override
    @Cacheable(cacheNames = USER_MENU_CACHE_NAME, key = "'menu-tree:'+#userId")
    public List<UserMenuEntity> getUserMenuAsTree(String userId) {
        return TreeSupportEntity.list2tree(getUserMenuAsList(userId), UserMenuEntity::setChildren,
                (Predicate<UserMenuEntity>) menuEntity ->
                        // parentIdä¸ºç©ºæˆ–è€…ä¸º-1çš„è?œå?•åˆ™è®¤ä¸ºæ˜¯æ ¹è?œå?•
                        StringUtils.isEmpty(menuEntity.getParentId()) || "-1".equals(menuEntity.getParentId()));
    }

    @TransactionalEventListener(condition = "#event.all")
    @Caching(evict = {
            @CacheEvict(cacheNames = USER_MENU_CACHE_NAME, allEntries = true),
            @CacheEvict(cacheNames = USER_AUTH_CACHE_NAME, allEntries = true)
    })
    public void clearAllUserCache(ClearUserAuthorizationCacheEvent event) {
        logger.debug("clear all user authorization cache");
    }

    @TransactionalEventListener(condition = "!#event.all")
    @Caching(
            evict = {
                    @CacheEvict(value = CacheConstants.USER_AUTH_CACHE_NAME, key = "#event.getUserId()"),
                    @CacheEvict(value = CacheConstants.USER_MENU_CACHE_NAME, key = "'user-menu-list:'+#event.getUserId()"),
                    @CacheEvict(value = CacheConstants.USER_MENU_CACHE_NAME, key = "'menu-tree:'+#event.getUserId()")
            }
    )
    public void clearUserCache(ClearUserAuthorizationCacheEvent event) {
        logger.debug("clear user:{} authorization cache", event.getUserId());
    }


    @Override
    public Authentication initUserAuthorization(String userId) {
        if (null == userId) {
            return null;
        }
        UserEntity userEntity = userService.selectByPk(userId);
        if (userEntity == null) {
            return null;
        }
        SimpleAuthentication authentication = new SimpleAuthentication();
        // ç”¨æˆ·ä¿¡æ?¯
        authentication.setUser(SimpleUser.builder()
                .id(userId)
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .type("default")
                .build());
        //è§’è‰²
        authentication.setRoles(userService.getUserRole(userId)
                .stream()
                .map(role -> new SimpleRole(role.getId(), role.getName()))
                .collect(Collectors.toList()));

        List<String> settingIdList = getUserSetting(userId)
                .stream()
                .map(AuthorizationSettingEntity::getId)
                .collect(Collectors.toList());

        if (settingIdList.isEmpty()) {
            authentication.setPermissions(new ArrayList<>());
            return authentication;
        }

        // where status=1 and setting_id in (?,?,?)
        List<AuthorizationSettingDetailEntity> detailList = DefaultDSLQueryService
                .createQuery(authorizationSettingDetailDao)
                .where(status, STATE_OK)
                .and().in(settingId, settingIdList)
                .listNoPaging();

        authentication.setPermissions(initPermission(detailList));

        return authentication;
    }

    @Override
    public List<Permission> initPermission(String type, String settingFor) {
        AuthorizationSettingEntity entity = select(type, settingFor);
        if (entity == null) {
            return new ArrayList<>();
        }
        List<AuthorizationSettingDetailEntity> detailList = DefaultDSLQueryService
                .createQuery(authorizationSettingDetailDao)
                .where(status, STATE_OK)
                .and().is(settingId, entity.getId())
                .listNoPaging();
        if (CollectionUtils.isEmpty(detailList)) {
            return new ArrayList<>();
        }
        return initPermission(detailList);
    }

    @Getter
    @Setter
    static class ParentPermissionDetail extends ParentPermission {
        private String sourcePermission;

        public static Stream<ParentPermissionDetail> of(PermissionEntity entity) {
            if (isEmpty(entity.getParents())) {
                return Stream.empty();
            }
            return entity.getParents()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(parent -> {
                        ParentPermissionDetail permissionDetail = new ParentPermissionDetail();
                        permissionDetail.setActions(parent.getActions());
                        permissionDetail.setSourcePermission(entity.getId());
                        permissionDetail.setPreActions(parent.getPreActions());
                        permissionDetail.setPermission(parent.getPermission());
                        return permissionDetail;
                    });
        }
    }

    private List<Permission> initPermission(List<AuthorizationSettingDetailEntity> detailList) {
        //æ?ƒé™?idé›†å?ˆ
//        List<String> permissionIds = detailList.stream()
//                .map(AuthorizationSettingDetailEntity::getPermissionId)
//                .distinct()
//                .collect(Collectors.toList());
        //æ?ƒé™?ä¿¡æ?¯ç¼“å­˜
        Map<String, PermissionEntity> permissionEntityCache = permissionService.select()
                .stream()
                .collect(Collectors.toMap(PermissionEntity::getId, Function.identity()));

        //é˜²æ­¢è¶Šæ?ƒ
        detailList = detailList.stream().filter(detail -> {
            PermissionEntity entity = permissionEntityCache.get(detail.getPermissionId());
            if (entity == null || !STATUS_ENABLED.equals(entity.getStatus())) {
                return false;
            }
            List<String> allActions = entity.getActions().stream().map(ActionEntity::getAction).collect(Collectors.toList());

            if (isNotEmpty(entity.getActions()) && isNotEmpty(detail.getActions())) {

                detail.setActions(detail.getActions().stream().filter(allActions::contains).collect(Collectors.toSet()));
            }
            if (isEmpty(entity.getSupportDataAccessTypes())) {
                detail.setDataAccesses(new java.util.ArrayList<>());
            } else if (isNotEmpty(detail.getDataAccesses()) && !entity.getSupportDataAccessTypes().contains("*")) {
                //é‡?æž„ä¸ºæ?ƒé™?æ”¯æŒ?çš„æ•°æ?®æ?ƒé™?æŽ§åˆ¶æ–¹å¼?,é˜²æ­¢è¶Šæ?ƒè®¾ç½®æ?ƒé™?
                detail.setDataAccesses(detail
                        .getDataAccesses()
                        .stream()
                        .filter(access ->
                                //ä»¥è®¾ç½®æ”¯æŒ?çš„æ?ƒé™?å¼€å¤´å°±è®¤ä¸ºæ‹¥æœ‰è¯¥æ?ƒé™?
                                //æ¯”å¦‚æ”¯æŒ?çš„æ?ƒé™?ä¸ºCUSTOM_SCOPE_ORG_SCOPE
                                //è®¾ç½®çš„æ?ƒé™?ä¸ºCUSTOM_SCOPE åˆ™é€šè¿‡æ£€éªŒ
                                entity.getSupportDataAccessTypes().stream()
                                        .anyMatch(type -> type.startsWith(access.getType())))
                        .collect(Collectors.toList()));
            }
            return true;
        }).collect(Collectors.toList());

        //å…¨éƒ¨æ?ƒé™?è®¾ç½®
        Map<String, List<AuthorizationSettingDetailEntity>> settings = detailList
                .stream()
                .collect(Collectors.groupingBy(AuthorizationSettingDetailEntity::getPermissionId));

        List<Permission> permissions = new ArrayList<>();

        settings.forEach((permissionId, details) -> {
            PermissionEntity entity = permissionEntityCache.get(permissionId);
            if (entity == null || !DataStatus.STATUS_ENABLED.equals(entity.getStatus())) {
                return;
            }
            SimplePermission permission = new SimplePermission();
            permission.setName(entity.getName());
            permission.setId(permissionId);
            Set<String> actions = new HashSet<>();
            Set<DataAccessConfig> dataAccessConfigs = new HashSet<>();
            //æŽ’åº?,æ ¹æ?®ä¼˜å…ˆçº§è¿›è¡ŒæŽ’åº?
            Collections.sort(details);
            for (AuthorizationSettingDetailEntity detail : details) {
                //å¦‚æžœæŒ‡å®šä¸?å?ˆå¹¶ç›¸å?Œçš„é…?ç½®,åˆ™æ¸…ç©ºä¹‹å‰?çš„é…?ç½®
                if (Boolean.FALSE.equals(detail.getMerge())) {
                    actions.clear();
                    dataAccessConfigs.clear();
                }
                // actions
                if (isNotEmpty(detail.getActions())) {
                    actions.addAll(detail.getActions());
                }
                // æ•°æ?®æ?ƒé™?æŽ§åˆ¶é…?ç½®
                if (isNotEmpty(detail.getDataAccesses())) {
                    dataAccessConfigs.addAll(detail.getDataAccesses()
                            .stream()
                            .map(dataAccessFactory::create)
                            .collect(Collectors.toSet()));
                }
            }

            permission.setActions(actions);
            permission.setDataAccesses(dataAccessConfigs);
            permissions.add(permission);
        });

        /*=============================è¿›è¡Œå…³è?”æ?ƒé™?å¤„ç?†============================================*/
        Map<String, Permission> permissionCache = permissions.stream()
                .collect(Collectors.toMap(Permission::getId, Function.identity()));

        //èŽ·å?–å…³è?”çš„æ?ƒé™?ä¿¡æ?¯
        Map<String, List<ParentPermissionDetail>> parentsPermissions = permissionEntityCache
                .values()
                .stream()
                .flatMap(ParentPermissionDetail::of)
                .collect(Collectors.groupingBy(ParentPermission::getPermission));

        //åˆ¤æ–­æ˜¯å?¦æ»¡è¶³å…³è?”æ?ƒé™?çš„æ?¡ä»¶
        Predicate<ParentPermissionDetail> preActionPredicate = parent -> {
            if (isEmpty(parent.getActions())) {
                return false;
            }
            if (isEmpty(parent.getPreActions())) {
                return true;
            }
            Permission source = permissionCache.get(parent.getSourcePermission());
            return source != null && source.getActions().containsAll(parent.getPreActions());
        };

        for (Permission permission : permissions) {
            //æœ‰å…¶ä»–æ?ƒé™?å…³è?”äº†æ­¤æ?ƒé™?
            List<ParentPermissionDetail> parents = parentsPermissions.get(permission.getId());
            if (parents != null) {
                //æ·»åŠ action
                permission.getActions()
                        .addAll(parents.stream()
                                .filter(preActionPredicate)
                                .map(ParentPermission::getActions)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toSet()));
                //åˆ é™¤è¢«å?ˆå¹¶çš„æ?ƒé™?é…?ç½®
                parentsPermissions.remove(permission.getId());
            }
        }
        //æ²¡æœ‰èµ‹äºˆè¢«å…³è?”çš„æ?ƒé™?æ—¶,ç›´æŽ¥å…³è?”æ?ƒé™?
        parentsPermissions.forEach((per, all) -> {
            PermissionEntity entity = permissionEntityCache.get(per);
            if (entity == null || !DataStatus.STATUS_ENABLED.equals(entity.getStatus())) {
                return;
            }
            SimplePermission permission = new SimplePermission();
            permission.setId(per);
            permission.setName(entity.getName());
            permission.setActions(all.stream()
                    .filter(preActionPredicate)
                    .map(ParentPermission::getActions)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet()));
            if (isEmpty(permission.getActions())) {
                return;
            }
            permissions.add(permission);
        });
        parentsPermissions.clear();
        permissionCache.clear();

        return permissions;
    }


    @Autowired
    public void setDataAccessFactory(DataAccessFactory dataAccessFactory) {
        this.dataAccessFactory = dataAccessFactory;
    }

    @Autowired
    public void setAuthorizationSettingTypeSuppliers(List<AuthorizationSettingTypeSupplier> authorizationSettingTypeSuppliers) {
        this.authorizationSettingTypeSuppliers = authorizationSettingTypeSuppliers;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAuthorizationSettingDao(AuthorizationSettingDao authorizationSettingDao) {
        this.authorizationSettingDao = authorizationSettingDao;
    }

    @Autowired
    public void setAuthorizationSettingDetailDao(AuthorizationSettingDetailDao authorizationSettingDetailDao) {
        this.authorizationSettingDetailDao = authorizationSettingDetailDao;
    }

    @Autowired
    public void setAuthorizationSettingMenuService(AuthorizationSettingMenuService authorizationSettingMenuService) {
        this.authorizationSettingMenuService = authorizationSettingMenuService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
