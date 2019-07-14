/** 
 * Finds closest root package for the given action path.
 */
public String findRootPackageForActionPath(final String actionPath){
  if (mappings == null) {
    return null;
  }
  int ndx=-1;
  int delta=Integer.MAX_VALUE;
  for (int i=0; i < mappings.length; i++) {
    String mapping=mappings[i];
    boolean found=false;
    if (actionPath.equals(mapping)) {
      found=true;
    }
 else {
      mapping+=StringPool.SLASH;
      if (actionPath.startsWith(mapping)) {
        found=true;
      }
    }
    if (found) {
      int distance=actionPath.length() - mapping.length();
      if (distance < delta) {
        ndx=i;
        delta=distance;
      }
    }
  }
  if (ndx == -1) {
    return null;
  }
  return packages[ndx];
}
