/** 
 * Returns individual application cache directory (for only image caching from ImageLoader). Cache directory will be created on SD card <i>("/Android/data/[app_package_name]/cache/uil-images")</i> if card is mounted and app has appropriate permission. Else - Android defines cache directory on device's file system.
 * @param context  Application context
 * @param cacheDir Cache directory path (e.g.: "AppCacheDir", "AppDir/cache/images")
 * @return Cache {@link File directory}
 */
public static File getIndividualCacheDirectory(Context context,String cacheDir){
  File appCacheDir=getCacheDirectory(context);
  File individualCacheDir=new File(appCacheDir,cacheDir);
  if (!individualCacheDir.exists()) {
    if (!individualCacheDir.mkdir()) {
      individualCacheDir=appCacheDir;
    }
  }
  return individualCacheDir;
}
