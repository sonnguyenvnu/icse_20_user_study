/** 
 * Create a folder. The folder may even be on external SD card for Kitkat.
 * @param dir The folder to be created.
 * @return True if creation was successful.
 */
public static boolean mkdir(Context context,@NonNull final File dir){
  boolean success=dir.exists();
  if (!success)   success=dir.mkdir();
  if (!success && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    DocumentFile document=getDocumentFile(context,dir,true,true);
    success=document != null && document.exists();
  }
  if (success)   scanFile(context,new String[]{dir.getPath()});
  return success;
}
