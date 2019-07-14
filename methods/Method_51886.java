public boolean returnsArray(){
  return !isVoid() && ((ASTType)jjtGetChild(0)).isArray();
}
