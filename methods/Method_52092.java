private String getSimpleForUpdateXpath(String itName){
  return "./StatementExpressionList[count(*)=1]" + "/StatementExpression" + "/*[self::PostfixExpression and @Image='++' or self::PreIncrementExpression]" + "/PrimaryExpression" + "/PrimaryPrefix" + "/Name" + (itName == null ? "" : "[@Image='" + itName + "']");
}
