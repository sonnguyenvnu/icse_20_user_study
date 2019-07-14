private static SQLExpr toSqlExpr(String sql){
  SQLExprParser parser=new ElasticSqlExprParser(sql);
  SQLExpr expr=parser.expr();
  if (parser.getLexer().token() != Token.EOF) {
    throw new ParserException("illegal sql expr : " + sql);
  }
  return expr;
}
