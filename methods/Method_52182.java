private boolean isJunit4Test(ASTMethodDeclaration node){
  return node.isAnnotationPresent("org.junit.Test");
}
