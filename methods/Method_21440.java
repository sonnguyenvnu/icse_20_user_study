private void initStatement(PreparedStatementHolder stmtHolder) throws SQLException {
  stmtHolder.incrementInUseCount();
  holder.getDataSource().initStatement(this,stmtHolder.statement);
}
