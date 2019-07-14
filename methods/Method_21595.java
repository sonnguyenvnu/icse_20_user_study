private static SQLMethodInvokeExpr convertBinaryOperatorToMethod(String operator,SQLBinaryOpExpr expr){
  SQLMethodInvokeExpr methodInvokeExpr=new SQLMethodInvokeExpr(operator,null);
  methodInvokeExpr.addParameter(expr.getLeft());
  methodInvokeExpr.addParameter(expr.getRight());
  return methodInvokeExpr;
}
