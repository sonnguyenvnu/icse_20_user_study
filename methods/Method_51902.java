@Override public boolean hasSuppressWarningsAnnotationFor(Rule rule){
  for (int i=0; i < jjtGetNumChildren(); i++) {
    if (jjtGetChild(i) instanceof ASTAnnotation) {
      ASTAnnotation a=(ASTAnnotation)jjtGetChild(i);
      if (a.suppresses(rule)) {
        return true;
      }
    }
  }
  return false;
}
