private boolean isAnnotatedOverride(ASTMethodDeclaration decl){
  List<ASTMarkerAnnotation> annotations=decl.jjtGetParent().findDescendantsOfType(ASTMarkerAnnotation.class);
  for (  ASTMarkerAnnotation ann : annotations) {
    if (ann.getFirstChildOfType(ASTName.class).getImage().equals("Override")) {
      return true;
    }
  }
  return false;
}
