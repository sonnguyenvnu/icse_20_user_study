public Authentication toAuthentication(DataAccessConfigBuilderFactory factory){
  SimpleAuthentication authentication=new SimpleAuthentication();
  SimpleUser user=new SimpleUser();
  user.setId(id);
  user.setName(name);
  user.setUsername(username);
  user.setType(type);
  authentication.setUser(user);
  authentication.setRoles((List)roles);
  List<Permission> permissionList=new ArrayList<>();
  permissionList.addAll(permissions.stream().map(info -> {
    SimplePermission permission=new SimplePermission();
    permission.setId(info.getId());
    permission.setName(info.getName());
    permission.setActions(info.getActions());
    permission.setDataAccesses(info.getDataAccesses().stream().map(conf -> factory.create().fromJson(JSON.toJSONString(conf)).build()).collect(Collectors.toSet()));
    return permission;
  }
).collect(Collectors.toList()));
  permissionList.addAll(permissionsSimple.entrySet().stream().map(entry -> {
    SimplePermission permission=new SimplePermission();
    permission.setId(entry.getKey());
    permission.setActions(new HashSet<>(entry.getValue()));
    return permission;
  }
).collect(Collectors.toList()));
  authentication.setPermissions(permissionList);
  return authentication;
}
