@Override public boolean hasSuppressWarningsAnnotationFor(Rule rule){
  for (  ASTModifierNode modifier : findChildrenOfType(ASTModifierNode.class)) {
    for (    ASTAnnotation a : modifier.findChildrenOfType(ASTAnnotation.class)) {
      if (a.suppresses(rule)) {
        return true;
      }
    }
  }
  return false;
}
