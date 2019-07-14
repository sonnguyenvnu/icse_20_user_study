private boolean isSafeVarargs(final ASTMethodDeclaration node){
  return node.isAnnotationPresent(SafeVarargs.class.getName());
}
