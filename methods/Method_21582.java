protected SQLExpr parseMatch(){
  MySqlMatchAgainstExpr matchAgainstExpr=new MySqlMatchAgainstExpr();
  if (lexer.token() == Token.RPAREN) {
    lexer.nextToken();
  }
 else {
    exprList(matchAgainstExpr.getColumns(),matchAgainstExpr);
    accept(Token.RPAREN);
  }
  acceptIdentifier("AGAINST");
  accept(Token.LPAREN);
  SQLExpr against=primary();
  matchAgainstExpr.setAgainst(against);
  if (lexer.token() == Token.IN) {
    lexer.nextToken();
    if (lexer.identifierEquals(FnvHash.Constants.NATURAL)) {
      lexer.nextToken();
      acceptIdentifier("LANGUAGE");
      acceptIdentifier("MODE");
      if (lexer.token() == Token.WITH) {
        lexer.nextToken();
        acceptIdentifier("QUERY");
        acceptIdentifier("EXPANSION");
        matchAgainstExpr.setSearchModifier(MySqlMatchAgainstExpr.SearchModifier.IN_NATURAL_LANGUAGE_MODE_WITH_QUERY_EXPANSION);
      }
 else {
        matchAgainstExpr.setSearchModifier(MySqlMatchAgainstExpr.SearchModifier.IN_NATURAL_LANGUAGE_MODE);
      }
    }
 else     if (lexer.identifierEquals(FnvHash.Constants.BOOLEAN)) {
      lexer.nextToken();
      acceptIdentifier("MODE");
      matchAgainstExpr.setSearchModifier(MySqlMatchAgainstExpr.SearchModifier.IN_BOOLEAN_MODE);
    }
 else {
      throw new ParserException("syntax error. " + lexer.info());
    }
  }
 else   if (lexer.token() == Token.WITH) {
    throw new ParserException("TODO. " + lexer.info());
  }
  accept(Token.RPAREN);
  return primaryRest(matchAgainstExpr);
}
