private boolean isJUnit3Method(ASTMethodDeclaration method){
  return isJUnit3Class && method.isVoid() && method.getMethodName().startsWith("test");
}
