protected SQLTableSource primaryTableSourceRest(SQLTableSource tableSource){
  parseIndexHintList(tableSource);
  if (lexer.token() == Token.PARTITION) {
    lexer.nextToken();
    accept(Token.LPAREN);
    this.exprParser.names(((SQLExprTableSource)tableSource).getPartitions(),tableSource);
    accept(Token.RPAREN);
  }
  return tableSource;
}
