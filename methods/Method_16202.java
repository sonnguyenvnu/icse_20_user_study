private List<Permission> initPermission(List<AuthorizationSettingDetailEntity> detailList){
  Map<String,PermissionEntity> permissionEntityCache=permissionService.select().stream().collect(Collectors.toMap(PermissionEntity::getId,Function.identity()));
  detailList=detailList.stream().filter(detail -> {
    PermissionEntity entity=permissionEntityCache.get(detail.getPermissionId());
    if (entity == null || !STATUS_ENABLED.equals(entity.getStatus())) {
      return false;
    }
    List<String> allActions=entity.getActions().stream().map(ActionEntity::getAction).collect(Collectors.toList());
    if (isNotEmpty(entity.getActions()) && isNotEmpty(detail.getActions())) {
      detail.setActions(detail.getActions().stream().filter(allActions::contains).collect(Collectors.toSet()));
    }
    if (isEmpty(entity.getSupportDataAccessTypes())) {
      detail.setDataAccesses(new java.util.ArrayList<>());
    }
 else     if (isNotEmpty(detail.getDataAccesses()) && !entity.getSupportDataAccessTypes().contains("*")) {
      detail.setDataAccesses(detail.getDataAccesses().stream().filter(access -> entity.getSupportDataAccessTypes().stream().anyMatch(type -> type.startsWith(access.getType()))).collect(Collectors.toList()));
    }
    return true;
  }
).collect(Collectors.toList());
  Map<String,List<AuthorizationSettingDetailEntity>> settings=detailList.stream().collect(Collectors.groupingBy(AuthorizationSettingDetailEntity::getPermissionId));
  List<Permission> permissions=new ArrayList<>();
  settings.forEach((permissionId,details) -> {
    PermissionEntity entity=permissionEntityCache.get(permissionId);
    if (entity == null || !DataStatus.STATUS_ENABLED.equals(entity.getStatus())) {
      return;
    }
    SimplePermission permission=new SimplePermission();
    permission.setName(entity.getName());
    permission.setId(permissionId);
    Set<String> actions=new HashSet<>();
    Set<DataAccessConfig> dataAccessConfigs=new HashSet<>();
    Collections.sort(details);
    for (    AuthorizationSettingDetailEntity detail : details) {
      if (Boolean.FALSE.equals(detail.getMerge())) {
        actions.clear();
        dataAccessConfigs.clear();
      }
      if (isNotEmpty(detail.getActions())) {
        actions.addAll(detail.getActions());
      }
      if (isNotEmpty(detail.getDataAccesses())) {
        dataAccessConfigs.addAll(detail.getDataAccesses().stream().map(dataAccessFactory::create).collect(Collectors.toSet()));
      }
    }
    permission.setActions(actions);
    permission.setDataAccesses(dataAccessConfigs);
    permissions.add(permission);
  }
);
  Map<String,Permission> permissionCache=permissions.stream().collect(Collectors.toMap(Permission::getId,Function.identity()));
  Map<String,List<ParentPermissionDetail>> parentsPermissions=permissionEntityCache.values().stream().flatMap(ParentPermissionDetail::of).collect(Collectors.groupingBy(ParentPermission::getPermission));
  Predicate<ParentPermissionDetail> preActionPredicate=parent -> {
    if (isEmpty(parent.getActions())) {
      return false;
    }
    if (isEmpty(parent.getPreActions())) {
      return true;
    }
    Permission source=permissionCache.get(parent.getSourcePermission());
    return source != null && source.getActions().containsAll(parent.getPreActions());
  }
;
  for (  Permission permission : permissions) {
    List<ParentPermissionDetail> parents=parentsPermissions.get(permission.getId());
    if (parents != null) {
      permission.getActions().addAll(parents.stream().filter(preActionPredicate).map(ParentPermission::getActions).flatMap(Collection::stream).collect(Collectors.toSet()));
      parentsPermissions.remove(permission.getId());
    }
  }
  parentsPermissions.forEach((per,all) -> {
    PermissionEntity entity=permissionEntityCache.get(per);
    if (entity == null || !DataStatus.STATUS_ENABLED.equals(entity.getStatus())) {
      return;
    }
    SimplePermission permission=new SimplePermission();
    permission.setId(per);
    permission.setName(entity.getName());
    permission.setActions(all.stream().filter(preActionPredicate).map(ParentPermission::getActions).flatMap(Collection::stream).collect(Collectors.toSet()));
    if (isEmpty(permission.getActions())) {
      return;
    }
    permissions.add(permission);
  }
);
  parentsPermissions.clear();
  permissionCache.clear();
  return permissions;
}
