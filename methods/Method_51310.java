/** 
 * Checks if the current version is a snapshot.
 * @return True if a snapshot release, false otherwise
 */
public static boolean isSnapshot(){
  return VERSION.endsWith("-SNAPSHOT");
}
