public static synchronized void init(IPlatform platform){
  if (sInstance == null) {
    sInstance=new FlutterBoostPlugin(platform);
    platform.getApplication().registerActivityLifecycleCallbacks(sInstance);
    ServiceLoader.load();
  }
}
