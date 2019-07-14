/** 
 * Compare two paths after normalization of them.
 * @param path1 first path for comparison
 * @param path2 second path for comparison
 * @return whether the two paths are equivalent after normalization
 */
public static boolean pathEquals(String path1,String path2){
  return cleanPath(path1).equals(cleanPath(path2));
}
