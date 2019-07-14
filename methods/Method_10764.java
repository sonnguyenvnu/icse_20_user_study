/** 
 * ??????
 * @param context
 * @return
 */
public static String getDiskCacheDir(Context context){
  String cachePath=null;
  if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
    cachePath=context.getExternalCacheDir().getPath();
  }
 else {
    cachePath=context.getCacheDir().getPath();
  }
  return cachePath;
}
