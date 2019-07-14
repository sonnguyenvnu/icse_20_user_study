public Select parseSelect(SQLQueryExpr mySqlExpr) throws SqlParseException {
  MySqlSelectQueryBlock query=(MySqlSelectQueryBlock)mySqlExpr.getSubQuery().getQuery();
  Select select=parseSelect(query);
  return select;
}
