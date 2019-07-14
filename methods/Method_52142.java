private boolean isUtilityClass(ASTAnyTypeDeclaration node){
  if (node.getTypeKind() != TypeKind.CLASS) {
    return false;
  }
  ASTClassOrInterfaceDeclaration classNode=(ASTClassOrInterfaceDeclaration)node;
  if (classNode.getSuperClassTypeNode() != null || !classNode.getSuperInterfacesTypeNodes().isEmpty()) {
    return false;
  }
  boolean hasAny=false;
  for (  ASTAnyTypeBodyDeclaration decl : classNode.getDeclarations()) {
switch (decl.getKind()) {
case FIELD:
case METHOD:
      hasAny=isNonPrivate(decl) && !isMainMethod(decl);
    if (!((AccessNode)decl.getDeclarationNode()).isStatic()) {
      return false;
    }
  break;
case INITIALIZER:
if (!((ASTInitializer)decl.getDeclarationNode()).isStatic()) {
  return false;
}
break;
default :
break;
}
}
return hasAny;
}
