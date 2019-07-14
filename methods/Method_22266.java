@Override public <T extends Plugin>List<T> load(@NonNull Class<T> clazz){
  List<T> list=new ArrayList<>();
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"SimplePluginLoader loading services from plugin classes : " + plugins);
  for (  Class<? extends Plugin> plugin : plugins) {
    if (clazz.isAssignableFrom(plugin)) {
      try {
        list.add((T)plugin.newInstance());
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Loaded plugin from class : " + plugin);
      }
 catch (      Exception e) {
        if (ACRA.DEV_LOGGING)         ACRA.log.w(LOG_TAG,"Could not load plugin from class : " + plugin,e);
      }
    }
  }
  return list;
}
