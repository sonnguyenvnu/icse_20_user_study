private boolean isAllowedMethodOnConditionLeft(SQLMethodInvokeExpr method,SQLBinaryOperator operator){
  return (method.getMethodName().toLowerCase().equals("nested") || method.getMethodName().toLowerCase().equals("children") || SQLFunctions.buildInFunctions.contains(method.getMethodName().toLowerCase())) && !operator.isLogical();
}
