public boolean tryFillFromExpr(SQLExpr expr) throws SqlParseException {
  if (!(expr instanceof SQLMethodInvokeExpr))   return false;
  SQLMethodInvokeExpr method=(SQLMethodInvokeExpr)expr;
  String methodName=method.getMethodName();
  if (!methodName.toLowerCase().equals("children"))   return false;
  List<SQLExpr> parameters=method.getParameters();
  if (parameters.size() != 2)   throw new SqlParseException("on children object only allowed 2 parameters (type, field)/(type, conditions...) ");
  String type=Util.extendedToString(parameters.get(0));
  this.childType=type;
  SQLExpr secondParameter=parameters.get(1);
  if (secondParameter instanceof SQLTextLiteralExpr || secondParameter instanceof SQLIdentifierExpr || secondParameter instanceof SQLPropertyExpr) {
    this.field=Util.extendedToString(secondParameter);
    this.simple=true;
  }
 else {
    Where where=Where.newInstance();
    new WhereParser(new SqlParser()).parseWhere(secondParameter,where);
    if (where.getWheres().size() == 0)     throw new SqlParseException("unable to parse filter where.");
    this.where=where;
    simple=false;
  }
  return true;
}
