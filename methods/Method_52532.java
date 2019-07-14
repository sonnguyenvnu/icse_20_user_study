public static boolean isSubtypeable(JavaTypeDefinition parameter,ASTExpression argument){
  if (argument.getTypeDefinition() == null) {
    LOG.log(Level.FINE,"No type information for node {0}",argument.toString());
    return true;
  }
  return isSubtypeable(parameter,argument.getTypeDefinition());
}
