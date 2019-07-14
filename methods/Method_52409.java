@Override protected boolean isViolationArgument(Node arg){
  return ((ASTLiteral)arg).isSingleCharacterStringLiteral();
}
