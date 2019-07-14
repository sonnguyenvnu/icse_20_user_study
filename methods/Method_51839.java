@Override public boolean hasSuppressWarningsAnnotationFor(Rule rule){
  for (  ASTAnnotation a : findChildrenOfType(ASTAnnotation.class)) {
    if (a.suppresses(rule)) {
      return true;
    }
  }
  return false;
}
