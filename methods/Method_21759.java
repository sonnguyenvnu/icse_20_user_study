public static Object expr2Object(SQLExpr expr,String charWithQuote){
  Object value=null;
  if (expr instanceof SQLNumericLiteralExpr) {
    value=((SQLNumericLiteralExpr)expr).getNumber();
  }
 else   if (expr instanceof SQLCharExpr) {
    value=charWithQuote + ((SQLCharExpr)expr).getText() + charWithQuote;
  }
 else   if (expr instanceof SQLIdentifierExpr) {
    value=expr.toString();
  }
 else   if (expr instanceof SQLPropertyExpr) {
    value=expr.toString();
  }
 else   if (expr instanceof SQLVariantRefExpr) {
    value=expr.toString();
  }
 else   if (expr instanceof SQLAllColumnExpr) {
    value="*";
  }
 else   if (expr instanceof SQLValuableExpr) {
    value=((SQLValuableExpr)expr).getValue();
  }
 else   if (expr instanceof SQLBooleanExpr) {
    value=((SQLBooleanExpr)expr).getValue();
  }
 else {
  }
  return value;
}
