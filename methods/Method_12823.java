/** 
 * Checks a file to make sure it should be packaged as standard resources.
 * @param fileName the name of the file (including extension)
 * @param allowClassFiles whether to allow java class files
 * @return true if the file should be packaged as standard java resources.
 */
public static boolean checkFileForPackaging(@NonNull String fileName,boolean allowClassFiles){
  String[] fileSegments=fileName.split("\\.");
  String fileExt="";
  if (fileSegments.length > 1) {
    fileExt=fileSegments[fileSegments.length - 1];
  }
  return checkFileForPackaging(fileName,fileExt,allowClassFiles);
}
