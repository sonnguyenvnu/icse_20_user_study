@Override protected List<MethodLikeNode> findOperations(ASTAnyTypeDeclaration node){
  List<MethodLikeNode> operations=new ArrayList<>();
  for (  ASTAnyTypeBodyDeclaration decl : node.getDeclarations()) {
    if (decl.jjtGetNumChildren() > 0 && decl.jjtGetChild(0) instanceof ASTMethodOrConstructorDeclaration) {
      operations.add((MethodLikeNode)decl.jjtGetChild(0));
    }
  }
  return operations;
}
