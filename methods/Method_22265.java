private <T extends Plugin>List<T> loadInternal(@NonNull Class<T> clazz,Predicate<T> shouldLoadPredicate){
  List<T> plugins=new ArrayList<>();
  final ServiceLoader<T> serviceLoader=ServiceLoader.load(clazz,getClass().getClassLoader());
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"ServicePluginLoader loading services from ServiceLoader : " + serviceLoader);
  for (final Iterator<T> iterator=serviceLoader.iterator(); iterator.hasNext(); ) {
    try {
      final T plugin=iterator.next();
      if (shouldLoadPredicate.apply(plugin)) {
        if (ACRA.DEV_LOGGING)         ACRA.log.d(ACRA.LOG_TAG,"Loaded " + clazz.getSimpleName() + " of type " + plugin.getClass().getName());
        plugins.add(plugin);
      }
 else {
        if (ACRA.DEV_LOGGING)         ACRA.log.d(LOG_TAG,"Ignoring disabled " + clazz.getSimpleName() + " of type " + plugin.getClass().getSimpleName());
      }
    }
 catch (    ServiceConfigurationError e) {
      ACRA.log.e(ACRA.LOG_TAG,"Unable to load " + clazz.getSimpleName(),e);
    }
  }
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Found services (" + plugins + ") for class : " + clazz);
  return plugins;
}
