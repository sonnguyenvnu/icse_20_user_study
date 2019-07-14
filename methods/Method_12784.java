private static PluginManager createInstance(Context context){
  try {
    Bundle metaData=context.getPackageManager().getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA).metaData;
    if (metaData == null) {
      return new PluginManager(context);
    }
    String factoryClass=metaData.getString("VA_FACTORY");
    if (factoryClass == null) {
      return new PluginManager(context);
    }
    PluginManager pluginManager=Reflector.on(factoryClass).method("create",Context.class).call(context);
    if (pluginManager != null) {
      Log.d(TAG,"Created a instance of " + pluginManager.getClass());
      return pluginManager;
    }
  }
 catch (  Exception e) {
    Log.w(TAG,"Created the instance error!",e);
  }
  return new PluginManager(context);
}
