/** 
 * Checks if the class loader could resolve a given class name (ie: it doesn't know for sure it will fail). Notice, that the ability to resolve a class does not imply that the class will actually be found and resolved.
 * @param name the name of the class
 * @return whether the class can be resolved
 */
public boolean couldResolve(String name){
  return !dontBother.containsKey(name);
}
