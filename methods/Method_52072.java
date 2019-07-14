@Override public Object visit(ASTConstructorDeclaration node,Object data){
  ASTFormalParameter[] arrs=getArrays(node.getParameters());
  List<ASTBlockStatement> bs=node.findDescendantsOfType(ASTBlockStatement.class);
  checkAll(data,arrs,bs);
  return data;
}
