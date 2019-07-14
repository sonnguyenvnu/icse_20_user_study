@Override public Object visit(final ASTName node,final Object data){
  final NameDeclaration nameDeclaration=node.getNameDeclaration();
  if (!(nameDeclaration instanceof VariableNameDeclaration)) {
    return data;
  }
  Class<?> type=((VariableNameDeclaration)nameDeclaration).getType();
  if (type == null || !type.getName().equals(LOGGER_CLASS)) {
    return data;
  }
  final ASTPrimaryExpression parentNode=node.getFirstParentOfType(ASTPrimaryExpression.class);
  final String method=parentNode.getFirstChildOfType(ASTPrimaryPrefix.class).getFirstChildOfType(ASTName.class).getImage().replace(nameDeclaration.getImage() + ".","");
  if (!LOGGER_LEVELS.contains(method)) {
    return data;
  }
  final List<ASTExpression> argumentList=parentNode.getFirstChildOfType(ASTPrimarySuffix.class).getFirstDescendantOfType(ASTArgumentList.class).findChildrenOfType(ASTExpression.class);
  final ASTExpression messageParam=argumentList.remove(0);
  final int expectedArguments=expectedArguments(messageParam);
  if (expectedArguments == 0) {
    return data;
  }
  if (argumentList.size() > expectedArguments) {
    removeThrowableParam(argumentList);
  }
  if (argumentList.size() < expectedArguments) {
    addViolationWithMessage(data,node,"Missing arguments," + getExpectedMessage(argumentList,expectedArguments));
  }
 else   if (argumentList.size() > expectedArguments) {
    addViolationWithMessage(data,node,"Too many arguments," + getExpectedMessage(argumentList,expectedArguments));
  }
  return data;
}
