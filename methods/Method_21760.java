public static Object getScriptValue(SQLExpr expr) throws SqlParseException {
  if (expr instanceof SQLIdentifierExpr || expr instanceof SQLPropertyExpr || expr instanceof SQLVariantRefExpr) {
    return "doc['" + expr.toString() + "'].value";
  }
 else   if (expr instanceof SQLValuableExpr) {
    return ((SQLValuableExpr)expr).getValue();
  }
  throw new SqlParseException("could not parse sqlBinaryOpExpr need to be identifier/valuable got" + expr.getClass().toString() + " with value:" + expr.toString());
}
