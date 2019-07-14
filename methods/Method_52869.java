private boolean doesElContainIdentifiers(ASTElExpression value){
  return value.getFirstDescendantOfType(ASTIdentifier.class) != null;
}
