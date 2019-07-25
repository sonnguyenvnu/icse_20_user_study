/** 
 * Compress the store by creating a new file and copying the live pages there. Temporarily, a file with the suffix ".tempFile" is created. This file is then renamed, replacing the original file, if possible. If not, the new file is renamed to ".newFile", then the old file is removed, and the new file is renamed. This might be interrupted, so it's better to compactCleanUp before opening a store, in case this method was used.
 * @param fileName the file name
 * @param compress whether to compress the data
 */
public static void compact(String fileName,boolean compress){
  String tempName=fileName + Constants.SUFFIX_MV_STORE_TEMP_FILE;
  FileUtils.delete(tempName);
  compact(fileName,tempName,compress);
  try {
    FileUtils.moveAtomicReplace(tempName,fileName);
  }
 catch (  DbException e) {
    String newName=fileName + Constants.SUFFIX_MV_STORE_NEW_FILE;
    FileUtils.delete(newName);
    FileUtils.move(tempName,newName);
    FileUtils.delete(fileName);
    FileUtils.move(newName,fileName);
  }
}
