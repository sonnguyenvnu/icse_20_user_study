@Override public Activity newActivity(ClassLoader cl,String className,Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
  try {
    cl.loadClass(className);
    Log.i(TAG,String.format("newActivity[%s]",className));
  }
 catch (  ClassNotFoundException e) {
    ComponentName component=PluginUtil.getComponent(intent);
    if (component == null) {
      return newActivity(mBase.newActivity(cl,className,intent));
    }
    String targetClassName=component.getClassName();
    Log.i(TAG,String.format("newActivity[%s : %s/%s]",className,component.getPackageName(),targetClassName));
    LoadedPlugin plugin=this.mPluginManager.getLoadedPlugin(component);
    if (plugin == null) {
      boolean debuggable=false;
      try {
        Context context=this.mPluginManager.getHostContext();
        debuggable=(context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
      }
 catch (      Throwable ex) {
      }
      if (debuggable) {
        throw new ActivityNotFoundException("error intent: " + intent.toURI());
      }
      Log.i(TAG,"Not found. starting the stub activity: " + StubActivity.class);
      return newActivity(mBase.newActivity(cl,StubActivity.class.getName(),intent));
    }
    Activity activity=mBase.newActivity(plugin.getClassLoader(),targetClassName,intent);
    activity.setIntent(intent);
    Reflector.QuietReflector.with(activity).field("mResources").set(plugin.getResources());
    return newActivity(activity);
  }
  return newActivity(mBase.newActivity(cl,className,intent));
}
