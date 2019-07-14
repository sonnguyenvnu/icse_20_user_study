private int getArgumentListArity(ASTArgumentList argList){
  if (argList != null) {
    return argList.jjtGetNumChildren();
  }
  return 0;
}
