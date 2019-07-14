private void addViolation(Object data,ASTVariableDeclarator variableDeclarator){
  super.addViolation(data,variableDeclarator,variableDeclarator.jjtGetChild(0).getImage());
}
