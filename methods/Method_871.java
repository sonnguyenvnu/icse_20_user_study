private boolean isStaticSimpleDateFormatCall(ASTPrimaryExpression primaryExpression,Set<String> localSimpleDateFormatNames){
  if (primaryExpression.jjtGetNumChildren() == 0) {
    return false;
  }
  ASTName name=primaryExpression.getFirstDescendantOfType(ASTName.class);
  if (name == null || name.getType() != SimpleDateFormat.class) {
    return false;
  }
  if (localSimpleDateFormatNames.contains(name.getNameDeclaration().getName())) {
    return false;
  }
  ASTPrimaryPrefix primaryPrefix=(ASTPrimaryPrefix)primaryExpression.jjtGetChild(0);
  if (primaryPrefix.getType() != SimpleDateFormat.class) {
    return false;
  }
  Token token=(Token)primaryPrefix.jjtGetLastToken();
  return FORMAT_METHOD_NAME.equals(token.image);
}
