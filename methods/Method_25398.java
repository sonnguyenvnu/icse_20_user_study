/** 
 * Match a method declaration with a specific name.
 * @param methodName The name of the method to match, e.g., "equals"
 */
public static Matcher<MethodTree> methodIsNamed(String methodName){
  return (methodTree,state) -> methodTree.getName().contentEquals(methodName);
}
