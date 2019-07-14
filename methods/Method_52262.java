private boolean hasArguments(ASTMethodDeclaration node){
  return node.hasDescendantMatchingXPath("./MethodDeclarator/FormalParameters/*");
}
