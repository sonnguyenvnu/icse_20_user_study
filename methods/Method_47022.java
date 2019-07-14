/** 
 * Check for a directory if it is possible to create files within this directory, either via normal writing or via Storage Access Framework.
 * @param folder The directory
 * @return true if it is possible to write in this directory.
 */
public static boolean isWritableNormalOrSaf(final File folder,Context c){
  if (folder == null)   return false;
  if (!folder.exists() || !folder.isDirectory()) {
    return false;
  }
  int i=0;
  File file;
  do {
    String fileName="AugendiagnoseDummyFile" + (++i);
    file=new File(folder,fileName);
  }
 while (file.exists());
  if (isWritable(file)) {
    return true;
  }
  DocumentFile document=getDocumentFile(file,false,c);
  if (document == null) {
    return false;
  }
  boolean result=document.canWrite() && file.exists();
  deleteFile(file,c);
  return result;
}
