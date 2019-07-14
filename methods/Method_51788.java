public int getArgumentCount(){
  if (this.jjtGetNumChildren() == 0) {
    return 0;
  }
  return this.jjtGetChild(0).jjtGetNumChildren();
}
