@Override public Object visit(ASTMethodDeclaration node,Object data){
  final ASTFormalParameters params=node.getFirstDescendantOfType(ASTFormalParameters.class);
  ASTFormalParameter[] arrs=getArrays(params);
  checkAll(data,arrs,node.findDescendantsOfType(ASTBlockStatement.class));
  return data;
}
