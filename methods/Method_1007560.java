public static void initialize(Plugin plugin){
  if (!isInitialized()) {
    instance=new PermissionsResolverManager(plugin);
  }
}
