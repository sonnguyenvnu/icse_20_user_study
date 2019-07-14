public static void hookResources(Context base,Resources resources){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    return;
  }
  try {
    Reflector reflector=Reflector.with(base);
    reflector.field("mResources").set(resources);
    Object loadedApk=reflector.field("mPackageInfo").get();
    Reflector.with(loadedApk).field("mResources").set(resources);
    Object activityThread=ActivityThread.currentActivityThread();
    Object resManager;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      resManager=android.app.ResourcesManager.getInstance();
    }
 else {
      resManager=Reflector.with(activityThread).field("mResourcesManager").get();
    }
    Map<Object,WeakReference<Resources>> map=Reflector.with(resManager).field("mActiveResources").get();
    Object key=map.keySet().iterator().next();
    map.put(key,new WeakReference<>(resources));
  }
 catch (  Exception e) {
    Log.w(TAG,e);
  }
}
