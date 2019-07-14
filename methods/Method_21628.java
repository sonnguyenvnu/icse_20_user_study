private boolean explanSpecialCondWithBothSidesAreLiterals(SQLBinaryOpExpr bExpr,Where where) throws SqlParseException {
  if ((bExpr.getLeft() instanceof SQLNumericLiteralExpr || bExpr.getLeft() instanceof SQLCharExpr) && (bExpr.getRight() instanceof SQLNumericLiteralExpr || bExpr.getRight() instanceof SQLCharExpr)) {
    SQLMethodInvokeExpr sqlMethodInvokeExpr=new SQLMethodInvokeExpr("script",null);
    String operator=bExpr.getOperator().getName();
    if (operator.equals("=")) {
      operator="==";
    }
    sqlMethodInvokeExpr.addParameter(new SQLCharExpr(Util.expr2Object(bExpr.getLeft(),"'") + " " + operator + " " + Util.expr2Object(bExpr.getRight(),"'")));
    explanCond("AND",sqlMethodInvokeExpr,where);
    return true;
  }
  return false;
}
