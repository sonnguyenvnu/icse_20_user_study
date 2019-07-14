@Override public boolean hasSuppressWarningsAnnotationFor(Rule rule){
  ASTVariableDeclarationStatements parent=(ASTVariableDeclarationStatements)jjtGetParent();
  for (  ASTModifierNode modifier : parent.findChildrenOfType(ASTModifierNode.class)) {
    for (    ASTAnnotation a : modifier.findChildrenOfType(ASTAnnotation.class)) {
      if (a.suppresses(rule)) {
        return true;
      }
    }
  }
  return false;
}
