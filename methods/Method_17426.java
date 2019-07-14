/** 
 * Returns if the path should be included. The request is ignored if it is a search query, a page revision, related to users or user management, or talk pages.
 */
public boolean isAllowed(String path){
  for (  String filter : STARTS_WITH_FILTER) {
    if (path.startsWith(filter)) {
      return false;
    }
  }
  for (  String filter : CONTAINS_FILTER) {
    if (path.contains(filter)) {
      return false;
    }
  }
  return true;
}
