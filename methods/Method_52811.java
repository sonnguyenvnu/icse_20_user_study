private boolean usesSimpleParameters(List<ASTArgument> arguments){
  for (  ASTArgument argument : arguments) {
    if (argument.jjtGetNumChildren() == 1) {
      return true;
    }
  }
  return false;
}
