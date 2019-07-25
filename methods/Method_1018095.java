@Override public void remove(String secretId){
  vaultTemplate.delete(getAbsolutePath(secretId,DEFAULTS));
  String usersPath=getAbsolutePath(secretId,USERS);
  List<String> users=vaultTemplate.list(usersPath);
  users.forEach(u -> vaultTemplate.delete(getPathForPrincipalName(usersPath,u)));
  String groupsPath=getAbsolutePath(secretId,GROUPS);
  List<String> groups=vaultTemplate.list(groupsPath);
  groups.forEach(g -> vaultTemplate.delete(getPathForPrincipalName(groupsPath,g)));
}
