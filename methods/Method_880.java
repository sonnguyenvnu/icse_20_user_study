private boolean returnThread(ASTMethodDeclaration methodDeclaration){
  ASTResultType resultType=methodDeclaration.getFirstChildOfType(ASTResultType.class);
  ASTType type=resultType.getFirstChildOfType(ASTType.class);
  return type != null && type.getType() == Thread.class;
}
