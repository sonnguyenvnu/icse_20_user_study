private boolean isCond(SQLBinaryOpExpr expr){
  SQLExpr leftSide=expr.getLeft();
  if (leftSide instanceof SQLMethodInvokeExpr) {
    return isAllowedMethodOnConditionLeft((SQLMethodInvokeExpr)leftSide,expr.getOperator());
  }
  return leftSide instanceof SQLIdentifierExpr || leftSide instanceof SQLPropertyExpr || leftSide instanceof SQLVariantRefExpr || leftSide instanceof SQLCastExpr;
}
