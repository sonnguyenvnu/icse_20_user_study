private ASTArgumentList getArgumentList(ASTArguments args){
  if (args != null) {
    return args.getFirstChildOfType(ASTArgumentList.class);
  }
  return null;
}
