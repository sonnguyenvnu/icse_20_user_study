private int checkType(){
  if (jjtGetNumChildren() == 0 || !(jjtGetChild(0) instanceof ASTType)) {
    return 0;
  }
  return ((ASTType)jjtGetChild(0)).getArrayDepth();
}
