/** 
 * Returns true if the class represented by this qualified name is in the unnamed package.
 */
@Override public boolean isUnnamedPackage(){
  return packages.isEmpty();
}
