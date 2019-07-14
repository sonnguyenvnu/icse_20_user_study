public MySqlOrderingExpr parseSelectGroupByItem(){
  MySqlOrderingExpr item=new MySqlOrderingExpr();
  item.setExpr(expr());
  if (lexer.token() == Token.ASC) {
    lexer.nextToken();
    item.setType(SQLOrderingSpecification.ASC);
  }
 else   if (lexer.token() == Token.DESC) {
    lexer.nextToken();
    item.setType(SQLOrderingSpecification.DESC);
  }
  return item;
}
