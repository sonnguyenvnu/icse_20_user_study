@Override public SQLUnionQuery unionRest(SQLUnionQuery union){
  if (lexer.token() == Token.LIMIT) {
    union.setLimit(this.exprParser.parseLimit());
  }
  return super.unionRest(union);
}
