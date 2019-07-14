private boolean doesNodeContainJUnitAnnotation(Node node,String annotationTypeClassName){
  List<ASTAnnotation> annotations=node.findDescendantsOfType(ASTAnnotation.class);
  for (  ASTAnnotation annotation : annotations) {
    Node annotationTypeNode=annotation.jjtGetChild(0);
    TypeNode annotationType=(TypeNode)annotationTypeNode;
    if (annotationType.getType() == null) {
      ASTName name=annotationTypeNode.getFirstChildOfType(ASTName.class);
      if (name != null && (name.hasImageEqualTo("Test") || name.hasImageEqualTo(annotationTypeClassName))) {
        return true;
      }
    }
 else     if (TypeHelper.isA(annotationType,annotationTypeClassName)) {
      return true;
    }
  }
  return false;
}
