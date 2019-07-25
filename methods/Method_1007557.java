@Override public void load(){
  userGroups=new HashMap<>();
  userPermissionsCache=new HashMap<>();
  defaultPermissionsCache=new HashSet<>();
  Map<String,Set<String>> userGroupPermissions=new HashMap<>();
  List<String> groupKeys=config.getKeys("permissions.groups");
  if (groupKeys != null) {
    for (    String key : groupKeys) {
      List<String> permissions=config.getStringList("permissions.groups." + key + ".permissions",null);
      if (!permissions.isEmpty()) {
        Set<String> groupPerms=new HashSet<>(permissions);
        userGroupPermissions.put(key,groupPerms);
        if (key.equals("default")) {
          defaultPermissionsCache.addAll(permissions);
        }
      }
    }
  }
  List<String> userKeys=config.getKeys("permissions.users");
  if (userKeys != null) {
    for (    String key : userKeys) {
      Set<String> permsCache=new HashSet<>();
      List<String> permissions=config.getStringList("permissions.users." + key + ".permissions",null);
      if (!permissions.isEmpty()) {
        permsCache.addAll(permissions);
      }
      List<String> groups=config.getStringList("permissions.users." + key + ".groups",null);
      groups.add("default");
      if (!groups.isEmpty()) {
        for (        String group : groups) {
          Set<String> groupPerms=userGroupPermissions.get(group);
          if (groupPerms != null) {
            permsCache.addAll(groupPerms);
          }
        }
      }
      userPermissionsCache.put(key.toLowerCase(Locale.ROOT),permsCache);
      userGroups.put(key.toLowerCase(Locale.ROOT),new HashSet<>(groups));
    }
  }
}
