protected SQLExpr parsePosition(){
  SQLExpr subStr=this.primary();
  accept(Token.IN);
  SQLExpr str=this.expr();
  accept(Token.RPAREN);
  SQLMethodInvokeExpr locate=new SQLMethodInvokeExpr("LOCATE");
  locate.addParameter(subStr);
  locate.addParameter(str);
  return primaryRest(locate);
}
