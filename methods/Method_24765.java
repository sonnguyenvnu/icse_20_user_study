/** 
 * Loads classes from .jar files in sketch classpath
 * @param typeName
 * @param child
 * @param noCompare
 * @return
 */
public static ArrayList<CompletionCandidate> getMembersForType(PreprocessedSketch ps,String typeName,String child,boolean noCompare,boolean staticOnly){
  ArrayList<CompletionCandidate> candidates=new ArrayList<>();
  log("In GMFT(), Looking for match " + child + " in class " + typeName + " noCompare " + noCompare + " staticOnly " + staticOnly);
  Class<?> probableClass=findClassIfExists(ps,typeName);
  if (probableClass == null) {
    log("In GMFT(), class not found.");
    return candidates;
  }
  return getMembersForType(ps,new ClassMember(probableClass),child,noCompare,staticOnly);
}
