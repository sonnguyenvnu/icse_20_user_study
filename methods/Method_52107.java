private boolean methodHasOverride(Node node){
  ASTClassOrInterfaceBodyDeclaration method=node.getFirstParentOfType(ASTClassOrInterfaceBodyDeclaration.class);
  if (method != null && method.jjtGetNumChildren() > 0 && method.jjtGetChild(0) instanceof ASTAnnotation) {
    ASTMarkerAnnotation marker=method.getFirstDescendantOfType(ASTMarkerAnnotation.class);
    if (marker != null && marker.getFirstChildOfType(ASTName.class) != null) {
      ASTName name=marker.getFirstChildOfType(ASTName.class);
      if (name.getType() == Override.class) {
        return true;
      }
    }
  }
  return false;
}
