public SQLColumnDefinition parseColumnRest(SQLColumnDefinition column){
  if (lexer.token() == Token.ON) {
    lexer.nextToken();
    accept(Token.UPDATE);
    SQLExpr expr=this.expr();
    column.setOnUpdate(expr);
  }
  if (lexer.identifierEquals(FnvHash.Constants.CHARACTER)) {
    lexer.nextToken();
    accept(Token.SET);
    MySqlCharExpr charSetCollateExpr=new MySqlCharExpr();
    charSetCollateExpr.setCharset(lexer.stringVal());
    lexer.nextToken();
    if (lexer.identifierEquals(FnvHash.Constants.COLLATE)) {
      lexer.nextToken();
      charSetCollateExpr.setCollate(lexer.stringVal());
      lexer.nextToken();
    }
    column.setCharsetExpr(charSetCollateExpr);
    return parseColumnRest(column);
  }
  if (lexer.identifierEquals(FnvHash.Constants.CHARSET)) {
    lexer.nextToken();
    MySqlCharExpr charSetCollateExpr=new MySqlCharExpr();
    charSetCollateExpr.setCharset(lexer.stringVal());
    lexer.nextToken();
    if (lexer.identifierEquals(FnvHash.Constants.COLLATE)) {
      lexer.nextToken();
      charSetCollateExpr.setCollate(lexer.stringVal());
      lexer.nextToken();
    }
    column.setCharsetExpr(charSetCollateExpr);
    return parseColumnRest(column);
  }
  if (lexer.identifierEquals(FnvHash.Constants.AUTO_INCREMENT)) {
    lexer.nextToken();
    column.setAutoIncrement(true);
    return parseColumnRest(column);
  }
  if (lexer.identifierEquals(FnvHash.Constants.PRECISION) && column.getDataType().nameHashCode64() == FnvHash.Constants.DOUBLE) {
    lexer.nextToken();
  }
  if (lexer.token() == Token.PARTITION) {
    throw new ParserException("syntax error " + lexer.info());
  }
  if (lexer.identifierEquals(FnvHash.Constants.STORAGE)) {
    lexer.nextToken();
    SQLExpr expr=expr();
    column.setStorage(expr);
  }
  if (lexer.token() == Token.AS) {
    lexer.nextToken();
    accept(Token.LPAREN);
    SQLExpr expr=expr();
    column.setAsExpr(expr);
    accept(Token.RPAREN);
  }
  if (lexer.identifierEquals(FnvHash.Constants.STORED)) {
    lexer.nextToken();
    column.setStored(true);
  }
  if (lexer.identifierEquals(FnvHash.Constants.VIRTUAL)) {
    lexer.nextToken();
    column.setVirtual(true);
  }
  super.parseColumnRest(column);
  return column;
}
