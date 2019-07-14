/** 
 * "/.." at the beginning should be removed as browsers do (not in RFC)
 */
private static String removeLeadingSlashPoints(String path){
  while (path.startsWith("/..")) {
    path=path.substring(3);
  }
  return path;
}
