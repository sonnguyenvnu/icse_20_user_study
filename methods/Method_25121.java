/** 
 * Removes all imports in a collection to this list of imports. Does not remove any imports that are not in the list.
 * @param importsToRemove a collection of imports to remove
 * @return true if any imports were removed from the list
 */
public boolean removeAll(Collection<String> importsToRemove){
  return importStrings.removeAll(importsToRemove);
}
