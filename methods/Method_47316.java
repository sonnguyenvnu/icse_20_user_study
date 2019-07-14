/** 
 * Helper method to get size of an otg folder
 */
public static long otgFolderSize(String path,final Context context){
  final AtomicLong totalBytes=new AtomicLong(0);
  OTGUtil.getDocumentFiles(path,context,file -> totalBytes.addAndGet(getBaseFileSize(file,context)));
  return totalBytes.longValue();
}
