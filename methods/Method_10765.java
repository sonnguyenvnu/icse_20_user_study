/** 
 * ??????????
 * @param context
 * @return
 */
public static String getDiskFileDir(Context context){
  String cachePath=null;
  if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
    cachePath=context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getPath();
  }
 else {
    cachePath=context.getFilesDir().getPath();
  }
  return cachePath;
}
