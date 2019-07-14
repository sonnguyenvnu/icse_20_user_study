/** 
 * Add all imports in a collection to this list of imports. Does not add any imports that are already in the list.
 * @param importsToAdd a collection of imports to add
 * @return true if any imports were added to the list
 */
public boolean addAll(Collection<String> importsToAdd){
  return importStrings.addAll(importsToAdd);
}
