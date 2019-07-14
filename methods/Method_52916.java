private boolean hasElseOrElseIf(final Node parentIfNode){
  return parentIfNode.getFirstChildOfType(ASTElseStatement.class) != null || parentIfNode.getFirstChildOfType(ASTElseIfStatement.class) != null;
}
