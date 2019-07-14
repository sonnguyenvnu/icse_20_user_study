private boolean throwsOneException(ASTMethodDeclaration node,Class<? extends Throwable> exception){
  ASTNameList throwsList=node.getThrows();
  if (throwsList != null && throwsList.jjtGetNumChildren() == 1) {
    ASTName n=(ASTName)throwsList.jjtGetChild(0);
    if (n.getType() == exception || exception.getSimpleName().equals(n.getImage()) || exception.getName().equals(n.getImage())) {
      return true;
    }
  }
  return false;
}
