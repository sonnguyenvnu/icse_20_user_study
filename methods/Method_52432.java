/** 
 * Tries to resolve a given typeImage as a generic Type. If the Generic Type is found, any defined ClassOrInterfaceType below this type declaration is used (this is typically a type bound, e.g.  {@code <T extends List>}.
 * @param argument the node, from where to start searching.
 * @param typeImage the type as string
 * @return the resolved class or <code>null</code> if nothing was found.
 */
private Class<?> resolveGenericType(Node argument,String typeImage){
  List<ASTTypeParameter> types=new ArrayList<>();
  ASTClassOrInterfaceBodyDeclaration firstParentOfType=argument.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
  if (firstParentOfType != null) {
    types.addAll(firstParentOfType.findDescendantsOfType(ASTTypeParameter.class));
  }
  List<ASTClassOrInterfaceDeclaration> enclosingClasses=argument.getParentsOfType(ASTClassOrInterfaceDeclaration.class);
  for (  ASTClassOrInterfaceDeclaration enclosing : enclosingClasses) {
    ASTTypeParameters classLevelTypeParameters=enclosing.getFirstChildOfType(ASTTypeParameters.class);
    if (classLevelTypeParameters != null) {
      types.addAll(classLevelTypeParameters.findDescendantsOfType(ASTTypeParameter.class));
    }
  }
  return resolveGenericType(typeImage,types);
}
