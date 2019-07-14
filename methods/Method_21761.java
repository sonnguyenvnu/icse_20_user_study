public static Object getScriptValueWithQuote(SQLExpr expr,String quote) throws SqlParseException {
  if (expr instanceof SQLIdentifierExpr || expr instanceof SQLPropertyExpr || expr instanceof SQLVariantRefExpr) {
    return "doc['" + expr.toString() + "'].value";
  }
 else   if (expr instanceof SQLCharExpr) {
    return quote + ((SQLCharExpr)expr).getValue() + quote;
  }
 else   if (expr instanceof SQLIntegerExpr) {
    return ((SQLIntegerExpr)expr).getValue();
  }
 else   if (expr instanceof SQLNumericLiteralExpr) {
    return ((SQLNumericLiteralExpr)expr).getNumber();
  }
 else   if (expr instanceof SQLNullExpr) {
    return ((SQLNullExpr)expr).toString().toLowerCase();
  }
 else   if (expr instanceof SQLBinaryOpExpr) {
    String left="doc['" + ((SQLBinaryOpExpr)expr).getLeft().toString() + "'].value";
    String operator=((SQLBinaryOpExpr)expr).getOperator().getName();
    String right="doc['" + ((SQLBinaryOpExpr)expr).getRight().toString() + "'].value";
    return left + operator + right;
  }
  throw new SqlParseException("could not parse sqlBinaryOpExpr need to be identifier/valuable got " + expr.getClass().toString() + " with value:" + expr.toString());
}
