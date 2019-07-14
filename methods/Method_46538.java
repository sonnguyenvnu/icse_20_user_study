@Override public String onBeforeAnyExecute(StatementInformation statementInformation) throws SQLException {
  String sql=statementInformation.getSqlWithValues();
  DTXLocalContext.cur().setResource(statementInformation.getStatement().getConnection());
  try {
    Statement statement=CCJSqlParserUtil.parse(sql);
    log.debug("statement > {}",statement);
    statementInformation.setAttachment(statement);
    if (statement instanceof Update) {
      sqlExecuteInterceptor.preUpdate((Update)statement);
    }
 else     if (statement instanceof Delete) {
      sqlExecuteInterceptor.preDelete((Delete)statement);
    }
 else     if (statement instanceof Insert) {
      sqlExecuteInterceptor.preInsert((Insert)statement);
    }
 else     if (statement instanceof Select) {
      sqlExecuteInterceptor.preSelect(new LockableSelect((Select)statement));
    }
  }
 catch (  JSQLParserException e) {
    throw new SQLException(e);
  }
  return sql;
}
