@Override public Object visit(ASTName node,Object data){
  if (!(node.jjtGetParent() instanceof ASTImportDeclaration) && !(node.jjtGetParent() instanceof ASTPackageDeclaration)) {
    if (node.getImage().indexOf('.') < 0) {
      return data;
    }
    checkImports(node,data);
  }
  return data;
}
