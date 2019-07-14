/** 
 * Returns the interfaces implemented by this class, or extended by this interface. Returns an empty list if none is specified.
 */
public List<ASTClassOrInterfaceType> getSuperInterfacesTypeNodes(){
  Iterable<ASTClassOrInterfaceType> it=isInterface() ? getFirstChildOfType(ASTExtendsList.class) : getFirstChildOfType(ASTImplementsList.class);
  return it == null ? Collections.<ASTClassOrInterfaceType>emptyList() : CollectionUtil.toList(it.iterator());
}
