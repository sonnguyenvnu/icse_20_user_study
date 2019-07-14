/** 
 * Returns a list with the type bounds of this node. The returned list has at least one element.
 */
public List<ASTClassOrInterfaceType> getBoundTypeNodes(){
  return findChildrenOfType(ASTClassOrInterfaceType.class);
}
