private void db2ValidationQueryCheck(){
  if (validationQuery == null) {
    return;
  }
  if (validationQuery.length() == 0) {
    return;
  }
  SQLStatementParser sqlStmtParser=SQLParserUtils.createSQLStatementParser(validationQuery,this.dbType);
  List<SQLStatement> stmtList=sqlStmtParser.parseStatementList();
  if (stmtList.size() != 1) {
    return;
  }
  SQLStatement stmt=stmtList.get(0);
  if (!(stmt instanceof SQLSelectStatement)) {
    return;
  }
  SQLSelectQuery query=((SQLSelectStatement)stmt).getSelect().getQuery();
  if (query instanceof SQLSelectQueryBlock) {
    if (((SQLSelectQueryBlock)query).getFrom() == null) {
      LOG.error("invalid db2 validationQuery. " + validationQuery + ", may should be : " + validationQuery + " FROM SYSDUMMY");
    }
  }
}
