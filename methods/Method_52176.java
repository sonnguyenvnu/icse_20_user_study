@Override public Object visit(ASTLocalVariableDeclaration node,Object data){
  ASTType type=node.getFirstChildOfType(ASTType.class);
  if (type != null && getProperty(CHECK_VARIABLES)) {
    List<ASTVariableDeclarator> variables=node.findChildrenOfType(ASTVariableDeclarator.class);
    for (    ASTVariableDeclarator variable : variables) {
      checkVariable(type,variable,data);
    }
  }
  return data;
}
