public static PermissionsResolver factory(Server server,YAMLProcessor config){
  if (server.getPluginManager().getPlugin("Vault") == null) {
    return null;
  }
  RegisteredServiceProvider<Permission> rsp=server.getServicesManager().getRegistration(Permission.class);
  if (rsp == null) {
    return null;
  }
  perms=rsp.getProvider();
  if (perms == null) {
    return null;
  }
  return new VaultResolver(server);
}
