public int getArgumentCount(){
  if (this.jjtGetNumChildren() == 1) {
    return ((ASTArguments)this.jjtGetChild(0)).getArgumentCount();
  }
 else {
    return ((ASTArguments)this.jjtGetChild(1)).getArgumentCount();
  }
}
