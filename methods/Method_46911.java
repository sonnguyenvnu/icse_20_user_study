/** 
 * Maintains a list of filesystems supporting the move/rename implementation. Please update to return your  {@link OpenMode} type if it is supported here
 * @return
 */
public static HashSet<OpenMode> getOperationSupportedFileSystem(){
  HashSet<OpenMode> hashSet=new HashSet<>();
  hashSet.add(OpenMode.SMB);
  hashSet.add(OpenMode.FILE);
  hashSet.add(OpenMode.DROPBOX);
  hashSet.add(OpenMode.BOX);
  hashSet.add(OpenMode.GDRIVE);
  hashSet.add(OpenMode.ONEDRIVE);
  return hashSet;
}
