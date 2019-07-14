private boolean isMainMethod(ASTAnyTypeBodyDeclaration bodyDeclaration){
  if (DeclarationKind.METHOD != bodyDeclaration.getKind()) {
    return false;
  }
  ASTMethodDeclaration decl=(ASTMethodDeclaration)bodyDeclaration.getDeclarationNode();
  return decl.isStatic() && "main".equals(decl.getMethodName()) && decl.getResultType().isVoid() && decl.getFormalParameters().getParameterCount() == 1 && String[].class.equals(decl.getFormalParameters().iterator().next().getType());
}
