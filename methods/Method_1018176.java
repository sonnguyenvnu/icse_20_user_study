/** 
 * Converts one or more strings to a path, compatible with the OS representation of a path
 * @param first The base path
 * @param more  Additional directories to join to create a path, if any
 * @return The completed, OS compatible path
 */
public static Path path(String first,String... more){
  return Paths.get(first,more);
}
