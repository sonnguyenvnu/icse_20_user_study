private boolean explanSpecialCondWithBothSidesAreProperty(SQLBinaryOpExpr bExpr,Where where) throws SqlParseException {
  if ((bExpr.getLeft() instanceof SQLPropertyExpr || bExpr.getLeft() instanceof SQLIdentifierExpr) && (bExpr.getRight() instanceof SQLPropertyExpr || bExpr.getRight() instanceof SQLIdentifierExpr) && Sets.newHashSet("=","<",">",">=","<=").contains(bExpr.getOperator().getName()) && !Util.isFromJoinOrUnionTable(bExpr)) {
    SQLMethodInvokeExpr sqlMethodInvokeExpr=new SQLMethodInvokeExpr("script",null);
    String operator=bExpr.getOperator().getName();
    if (operator.equals("=")) {
      operator="==";
    }
    String leftProperty=Util.expr2Object(bExpr.getLeft()).toString();
    String rightProperty=Util.expr2Object(bExpr.getRight()).toString();
    if (leftProperty.split("\\.").length > 1) {
      leftProperty=leftProperty.substring(leftProperty.split("\\.")[0].length() + 1);
    }
    if (rightProperty.split("\\.").length > 1) {
      rightProperty=rightProperty.substring(rightProperty.split("\\.")[0].length() + 1);
    }
    sqlMethodInvokeExpr.addParameter(new SQLCharExpr("doc['" + leftProperty + "'].value " + operator + " doc['" + rightProperty + "'].value"));
    explanCond("AND",sqlMethodInvokeExpr,where);
    return true;
  }
  return false;
}
