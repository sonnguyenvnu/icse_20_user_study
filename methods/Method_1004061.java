/** 
 * Checks whether execution data for classes with the given name are contained in the store.
 * @param name VM name
 * @return <code>true</code> if at least one class with the name iscontained.
 */
public boolean contains(final String name){
  return names.contains(name);
}
