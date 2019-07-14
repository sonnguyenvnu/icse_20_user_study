/** 
 * Returns a collection of method groups for given list of  {@code classMethods}. <p>A <i>method group</i> is a list of methods with the same name. <p>It is assumed that given  {@code classMethods} really do belong to the same class. Thereturned collection does not guarantee any particular group ordering.
 */
private static Collection<List<MethodTree>> getMethodGroups(List<MethodTree> classMethods){
  return classMethods.stream().collect(groupingBy(MethodTree::getName)).values();
}
