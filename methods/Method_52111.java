private boolean isFillInStackTraceCalled(final String target,final ASTVariableInitializer initializer){
  final ASTName astName=initializer.getFirstDescendantOfType(ASTName.class);
  return astName != null && astName.hasImageEqualTo(target + FILL_IN_STACKTRACE);
}
