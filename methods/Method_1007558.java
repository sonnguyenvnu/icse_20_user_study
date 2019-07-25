public static PermissionsResolver factory(Server server,YAMLProcessor config){
  File groups=new File("perms_groups.txt");
  File users=new File("perms_users.txt");
  if (!groups.exists() || !users.exists()) {
    return null;
  }
  return new FlatFilePermissionsResolver(groups,users);
}
