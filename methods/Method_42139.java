/** 
 * Delete a folder.
 * @param file The folder name.
 * @return true if successful.
 */
public static boolean rmdir(Context context,@NonNull final File file){
  if (!file.exists() && !file.isDirectory())   return false;
  String[] fileList=file.list();
  if (fileList != null && fileList.length > 0)   return false;
  if (file.delete())   return true;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    DocumentFile document=getDocumentFile(context,file,true,true);
    return document != null && document.delete();
  }
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
    ContentResolver resolver=context.getContentResolver();
    ContentValues values=new ContentValues();
    values.put(MediaStore.MediaColumns.DATA,file.getAbsolutePath());
    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
    resolver.delete(MediaStore.Files.getContentUri("external"),MediaStore.MediaColumns.DATA + "=?",new String[]{file.getAbsolutePath()});
  }
  return !file.exists();
}
