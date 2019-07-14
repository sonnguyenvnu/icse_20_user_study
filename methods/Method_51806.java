public boolean isEnumChild(){
  return jjtGetParent().jjtGetParent() instanceof ASTEnumConstant;
}
