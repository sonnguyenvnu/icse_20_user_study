protected void handleRBAC(Authentication authentication,AuthorizeDefinition definition){
  boolean access=true;
  Logical logical=definition.getLogical() == Logical.DEFAULT ? Logical.OR : definition.getLogical();
  boolean logicalIsOr=logical == Logical.OR;
  Set<String> permissionsDef=definition.getPermissions();
  Set<String> actionsDef=definition.getActions();
  Set<String> rolesDef=definition.getRoles();
  Set<String> usersDef=definition.getUser();
  if (!definition.getPermissions().isEmpty()) {
    if (logger.isInfoEnabled()) {
      logger.info("??????:??{}({}),??{}.",definition.getPermissionDescription(),permissionsDef,actionsDef);
    }
    List<Permission> permissions=authentication.getPermissions().stream().filter(permission -> {
      if (!permissionsDef.contains(permission.getId())) {
        return false;
      }
      if (actionsDef.isEmpty()) {
        return true;
      }
      List<String> actions=permission.getActions().stream().filter(actionsDef::contains).collect(Collectors.toList());
      if (actions.isEmpty()) {
        return false;
      }
      return logicalIsOr || permission.getActions().containsAll(actions);
    }
).collect(Collectors.toList());
    access=logicalIsOr ? CollectionUtils.isNotEmpty(permissions) : permissions.size() == permissionsDef.size();
  }
  if (!rolesDef.isEmpty()) {
    if (logger.isInfoEnabled()) {
      logger.info("do role access handle : roles{} , definition:{}",rolesDef,definition.getRoles());
    }
    Function<Predicate<Role>,Boolean> func=logicalIsOr ? authentication.getRoles().stream()::anyMatch : authentication.getRoles().stream()::allMatch;
    access=func.apply(role -> rolesDef.contains(role.getId()));
  }
  if (!usersDef.isEmpty()) {
    if (logger.isInfoEnabled()) {
      logger.info("do user access handle : users{} , definition:{} ",usersDef,definition.getUser());
    }
    Function<Predicate<String>,Boolean> func=logicalIsOr ? usersDef.stream()::anyMatch : usersDef.stream()::allMatch;
    access=func.apply(authentication.getUser().getUsername()::equals);
  }
  if (!access) {
    throw new AccessDenyException(definition.getMessage());
  }
}
