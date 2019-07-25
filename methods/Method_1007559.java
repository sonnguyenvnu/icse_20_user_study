public static PermissionsResolver factory(Server server,YAMLProcessor config){
  PluginManager pluginManager=server.getPluginManager();
  try {
    Class.forName("com.nijikokun.bukkit.Permissions.Permissions");
  }
 catch (  ClassNotFoundException e) {
    return null;
  }
  Plugin plugin=pluginManager.getPlugin("Permissions");
  if (!(plugin instanceof Permissions)) {
    return null;
  }
  if (config.getBoolean("ignore-nijiperms-bridges",true) && isFakeNijiPerms(plugin)) {
    return null;
  }
  return new NijiPermissionsResolver(server,(Permissions)plugin);
}
