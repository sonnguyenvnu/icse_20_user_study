/** 
 * Returns the superclass type node if this node is a class declaration and explicitly declares an  {@code extends}clause. Superinterfaces of an interface are not considered. <p>Returns  {@code null} otherwise.
 */
public ASTClassOrInterfaceType getSuperClassTypeNode(){
  if (isInterface()) {
    return null;
  }
  ASTExtendsList extendsList=getFirstChildOfType(ASTExtendsList.class);
  return extendsList == null ? null : extendsList.iterator().next();
}
