@Override public PreparedStatement prepareStatement(String sql) throws SQLException {
  checkState();
  PreparedStatementHolder stmtHolder=null;
  DruidPooledPreparedStatement.PreparedStatementKey key=new DruidPooledPreparedStatement.PreparedStatementKey(sql,getCatalog(),PreparedStatementPool.MethodType.M1);
  boolean poolPreparedStatements=holder.isPoolPreparedStatements();
  if (poolPreparedStatements) {
    stmtHolder=holder.getStatementPool().get(key);
  }
  if (stmtHolder == null) {
    try {
      stmtHolder=new PreparedStatementHolder(key,conn.prepareStatement(sql));
      holder.getDataSource().incrementPreparedStatementCount();
    }
 catch (    SQLException ex) {
      handleException(ex,sql);
    }
  }
  initStatement(stmtHolder);
  DruidPooledPreparedStatement rtnVal=new ElasticSearchDruidPooledPreparedStatement(this,stmtHolder);
  holder.addTrace(rtnVal);
  return rtnVal;
}
