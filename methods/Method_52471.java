/** 
 * Tries to resolve a class by name.
 * @param name the name of the class
 * @return the class or <code>null</code> if no class could be found
 */
public Class<?> resolveType(String name){
  return types.findClass(name);
}
