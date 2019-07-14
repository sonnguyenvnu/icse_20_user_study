private Object parseValue(SQLExpr expr) throws SqlParseException {
  if (expr instanceof SQLNumericLiteralExpr) {
    Number number=((SQLNumericLiteralExpr)expr).getNumber();
    if (number instanceof BigDecimal) {
      return number.doubleValue();
    }
    if (number instanceof BigInteger) {
      return number.longValue();
    }
    return ((SQLNumericLiteralExpr)expr).getNumber();
  }
 else   if (expr instanceof SQLCharExpr) {
    return ((SQLCharExpr)expr).getText();
  }
 else   if (expr instanceof SQLMethodInvokeExpr) {
    return expr;
  }
 else   if (expr instanceof SQLNullExpr) {
    return null;
  }
 else   if (expr instanceof SQLIdentifierExpr) {
    return expr;
  }
 else   if (expr instanceof SQLPropertyExpr) {
    return expr;
  }
 else {
    throw new SqlParseException(String.format("Failed to parse SqlExpression of type %s. expression value: %s",expr.getClass(),expr));
  }
}
