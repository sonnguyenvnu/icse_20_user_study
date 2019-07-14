/** 
 * Checks whether a folder and its content is valid for packaging into the .apk as standard Java resource.
 * @param folderName the name of the folder.
 * @return true if the folder is valid for packaging.
 */
public static boolean checkFolderForPackaging(@NonNull String folderName){
  return !folderName.equalsIgnoreCase("CVS") && !folderName.equalsIgnoreCase(".svn") && !folderName.equalsIgnoreCase("SCCS") && !folderName.startsWith("_");
}
