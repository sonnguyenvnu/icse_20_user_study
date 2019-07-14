protected SQLAggregateExpr parseAggregateExprRest(SQLAggregateExpr aggregateExpr){
  if (lexer.token() == Token.ORDER) {
    SQLOrderBy orderBy=this.parseOrderBy();
    aggregateExpr.putAttribute("ORDER BY",orderBy);
  }
  if (lexer.identifierEquals(FnvHash.Constants.SEPARATOR)) {
    lexer.nextToken();
    SQLExpr seperator=this.primary();
    seperator.setParent(aggregateExpr);
    aggregateExpr.putAttribute("SEPARATOR",seperator);
  }
  return aggregateExpr;
}
