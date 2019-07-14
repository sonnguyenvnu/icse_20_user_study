private static boolean isExternal(File directory,CacheErrorLogger cacheErrorLogger){
  boolean state=false;
  String appCacheDirPath=null;
  try {
    File extStoragePath=Environment.getExternalStorageDirectory();
    if (extStoragePath != null) {
      String cacheDirPath=extStoragePath.toString();
      try {
        appCacheDirPath=directory.getCanonicalPath();
        if (appCacheDirPath.contains(cacheDirPath)) {
          state=true;
        }
      }
 catch (      IOException e) {
        cacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER,TAG,"failed to read folder to check if external: " + appCacheDirPath,e);
      }
    }
  }
 catch (  Exception e) {
    cacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.OTHER,TAG,"failed to get the external storage directory!",e);
  }
  return state;
}
