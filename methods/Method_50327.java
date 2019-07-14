/** 
 * Returns specified application cache directory. Cache directory will be created on SD card by defined path if card is mounted and app has appropriate permission. Else - Android defines cache directory on device's file system.
 * @param context  Application context
 * @param cacheDir Cache directory path (e.g.: "AppCacheDir", "AppDir/cache/images")
 * @return Cache {@link File directory}
 */
public static File getOwnCacheDirectory(Context context,String cacheDir,boolean preferExternal){
  File appCacheDir=null;
  if (preferExternal && MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
    appCacheDir=new File(Environment.getExternalStorageDirectory(),cacheDir);
  }
  if (appCacheDir == null || (!appCacheDir.exists() && !appCacheDir.mkdirs())) {
    appCacheDir=context.getCacheDir();
  }
  return appCacheDir;
}
