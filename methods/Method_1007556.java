public static PermissionsResolver factory(Server server,YAMLProcessor config){
  try {
    Class.forName("de.bananaco.bpermissions.api.ApiLayer");
  }
 catch (  ClassNotFoundException e) {
    return null;
  }
  return new bPermissionsResolver(server);
}
