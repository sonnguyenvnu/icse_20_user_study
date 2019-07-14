private MethodField parseSQLMethodInvokeExprWithFunctionInWhere(SQLMethodInvokeExpr soExpr) throws SqlParseException {
  MethodField methodField=FieldMaker.makeMethodField(soExpr.getMethodName(),soExpr.getParameters(),null,null,query != null ? query.getFrom().getAlias() : null,false);
  return methodField;
}
