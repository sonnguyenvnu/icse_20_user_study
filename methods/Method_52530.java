public static boolean isMethodConvertible(JavaTypeDefinition parameter,ASTExpression argument){
  if (argument.getTypeDefinition() == null) {
    LOG.log(Level.FINE,"No type information for node {0}",argument.toString());
    return true;
  }
  return isMethodConvertible(parameter,argument.getTypeDefinition());
}
