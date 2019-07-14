public boolean tryParseFromMethodExpr(SQLMethodInvokeExpr expr) throws SqlParseException {
  if (!expr.getMethodName().toLowerCase().equals("script")) {
    return false;
  }
  List<SQLExpr> methodParameters=expr.getParameters();
  if (methodParameters.size() == 0) {
    return false;
  }
  script=Util.extendedToString(methodParameters.get(0));
  if (methodParameters.size() == 1) {
    return true;
  }
  args=new HashMap<>();
  for (int i=1; i < methodParameters.size(); i++) {
    SQLExpr innerExpr=methodParameters.get(i);
    if (!(innerExpr instanceof SQLBinaryOpExpr)) {
      return false;
    }
    SQLBinaryOpExpr binaryOpExpr=(SQLBinaryOpExpr)innerExpr;
    if (!binaryOpExpr.getOperator().getName().equals("=")) {
      return false;
    }
    SQLExpr right=binaryOpExpr.getRight();
    Object value=Util.expr2Object(right);
    String key=Util.extendedToString(binaryOpExpr.getLeft());
    if (key.equals("script_type")) {
      parseAndUpdateScriptType(value.toString());
    }
 else {
      args.put(key,value);
    }
  }
  return true;
}
