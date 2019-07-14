/** 
 * Check whether creation of new directory is inside the same directory with the same name or not Directory inside the same directory with similar filename shall not be allowed Doesn't work at an OTG path
 */
public static boolean isNewDirectoryRecursive(HybridFile file){
  return file.getName().equals(file.getParentName());
}
