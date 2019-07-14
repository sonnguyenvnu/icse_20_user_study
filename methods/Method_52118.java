private boolean hasOverrideAnnotation(ASTMethodDeclaration node){
  int childIndex=node.jjtGetChildIndex();
  for (int i=0; i < childIndex; i++) {
    Node previousSibling=node.jjtGetParent().jjtGetChild(i);
    List<ASTMarkerAnnotation> annotations=previousSibling.findDescendantsOfType(ASTMarkerAnnotation.class);
    for (    ASTMarkerAnnotation annotation : annotations) {
      ASTName name=annotation.getFirstChildOfType(ASTName.class);
      if (name != null && (name.hasImageEqualTo("Override") || name.hasImageEqualTo("java.lang.Override"))) {
        return true;
      }
    }
  }
  return false;
}
