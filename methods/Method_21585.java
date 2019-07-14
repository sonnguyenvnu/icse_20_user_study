@Override public MySqlPrimaryKey parsePrimaryKey(){
  accept(Token.PRIMARY);
  accept(Token.KEY);
  MySqlPrimaryKey primaryKey=new MySqlPrimaryKey();
  if (lexer.identifierEquals(FnvHash.Constants.USING)) {
    lexer.nextToken();
    primaryKey.setIndexType(lexer.stringVal());
    lexer.nextToken();
  }
  if (lexer.token() != Token.LPAREN) {
    SQLName name=this.name();
    primaryKey.setName(name);
  }
  accept(Token.LPAREN);
  for (; ; ) {
    SQLExpr expr;
    if (lexer.token() == Token.LITERAL_ALIAS) {
      expr=this.name();
    }
 else {
      expr=this.expr();
    }
    primaryKey.addColumn(expr);
    if (!(lexer.token() == (Token.COMMA))) {
      break;
    }
 else {
      lexer.nextToken();
    }
  }
  accept(Token.RPAREN);
  if (lexer.identifierEquals(FnvHash.Constants.USING)) {
    lexer.nextToken();
    primaryKey.setIndexType(lexer.stringVal());
    lexer.nextToken();
  }
  return primaryKey;
}
