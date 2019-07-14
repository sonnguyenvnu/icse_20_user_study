/** 
 * Create a folder. The folder may even be on external SD card for Kitkat.
 * @deprecated use {@link #mkdirs(Context,HybridFile)}
 * @param file  The folder to be created.
 * @return True if creation was successful.
 */
public static boolean mkdir(final File file,Context context){
  if (file == null)   return false;
  if (file.exists()) {
    return file.isDirectory();
  }
  if (file.mkdirs()) {
    return true;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && FileUtil.isOnExtSdCard(file,context)) {
    DocumentFile document=getDocumentFile(file,true,context);
    return document.exists();
  }
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
    try {
      return MediaStoreHack.mkdir(context,file);
    }
 catch (    IOException e) {
      return false;
    }
  }
  return false;
}
