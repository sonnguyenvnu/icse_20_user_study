public static PermissionsResolver factory(Server server,YAMLProcessor config){
  RegisteredServiceProvider<PermissionsProvider> serviceProvider=server.getServicesManager().getRegistration(PermissionsProvider.class);
  if (serviceProvider != null) {
    return new PluginPermissionsResolver(serviceProvider.getProvider(),serviceProvider.getPlugin());
  }
  for (  Plugin plugin : server.getPluginManager().getPlugins()) {
    if (plugin instanceof PermissionsProvider) {
      return new PluginPermissionsResolver((PermissionsProvider)plugin,plugin);
    }
  }
  return null;
}
