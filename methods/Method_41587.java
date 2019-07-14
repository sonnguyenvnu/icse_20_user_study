/** 
 * Finds a resource with a given name. This method returns null if no resource with this name is found.
 * @param name name of the desired resource
 * @return a java.io.InputStream object
 */
public InputStream getResourceAsStream(String name){
  InputStream result=null;
  if (bestCandidate != null) {
    result=bestCandidate.getResourceAsStream(name);
    if (result == null) {
      bestCandidate=null;
    }
 else {
      return result;
    }
  }
  ClassLoadHelper loadHelper=null;
  Iterator<ClassLoadHelper> iter=loadHelpers.iterator();
  while (iter.hasNext()) {
    loadHelper=iter.next();
    result=loadHelper.getResourceAsStream(name);
    if (result != null) {
      break;
    }
  }
  bestCandidate=loadHelper;
  return result;
}
