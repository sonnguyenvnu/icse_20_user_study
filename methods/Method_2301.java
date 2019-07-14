public static synchronized FrescoContext get(Resources resources){
  if (sInstance == null) {
    sInstance=createDefaultContext(resources);
  }
  return sInstance;
}
