private boolean extendsOrImplementsCloneable(final ASTClassOrInterfaceDeclaration node){
  if (node.getType() != null) {
    return Cloneable.class.isAssignableFrom(node.getType());
  }
  final ASTImplementsList impl=node.getFirstChildOfType(ASTImplementsList.class);
  if (impl != null) {
    for (int ix=0; ix < impl.jjtGetNumChildren(); ix++) {
      final Node child=impl.jjtGetChild(ix);
      if (child.getClass() != ASTClassOrInterfaceType.class) {
        continue;
      }
      final ASTClassOrInterfaceType type=(ASTClassOrInterfaceType)child;
      if (type.getType() == null) {
        if ("Cloneable".equals(type.getImage())) {
          return true;
        }
      }
 else       if (Cloneable.class.isAssignableFrom(type.getType())) {
        return true;
      }
    }
  }
  if (node.jjtGetNumChildren() != 0 && node.jjtGetChild(0) instanceof ASTExtendsList) {
    final ASTClassOrInterfaceType type=(ASTClassOrInterfaceType)node.jjtGetChild(0).jjtGetChild(0);
    final Class<?> clazz=type.getType();
    if (clazz != null) {
      return Cloneable.class.isAssignableFrom(clazz);
    }
  }
  return false;
}
