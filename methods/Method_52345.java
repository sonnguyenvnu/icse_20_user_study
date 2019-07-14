private boolean isNewThrowable(ASTPrimaryExpression last){
  ASTClassOrInterfaceType classOrInterface=last.getFirstDescendantOfType(ASTClassOrInterfaceType.class);
  return classOrInterface != null && classOrInterface.getType() != null && TypeHelper.isA(classOrInterface,Throwable.class);
}
