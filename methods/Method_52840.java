@Override public Object visit(ASTTypeSpecification node,Object data){
  createClassScope(node);
  Scope s=((PLSQLNode)node.jjtGetParent()).getScope();
  s.addDeclaration(new ClassNameDeclaration(node));
  cont(node);
  return data;
}
