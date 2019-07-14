/** 
 * Returns a file in the parent folder that does not exist yet. If parent/fileName already exists, this will look for parent/fileName(2) then parent/fileName(3) and so forth.
 * @return a file that does not exist yet
 */
public static File getUniqueName(File parentFolder,String fileName){
  File backupFolderForLib;
  int i=1;
  do {
    String folderName=fileName;
    if (i >= 2) {
      folderName+="(" + i + ")";
    }
    i++;
    backupFolderForLib=new File(parentFolder,folderName);
  }
 while (backupFolderForLib.exists());
  return backupFolderForLib;
}
