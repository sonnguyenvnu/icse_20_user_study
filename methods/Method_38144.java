/** 
 * Executes count queries and optionally closes query afterwards.
 */
protected long executeCount(final boolean close){
  start=System.currentTimeMillis();
  init();
  ResultSet rs=null;
  if (log.isDebugEnabled()) {
    log.debug("Executing prepared count: " + getQueryString());
  }
  try {
    if (preparedStatement == null) {
      rs=statement.executeQuery(query.sql);
    }
 else {
      rs=preparedStatement.executeQuery();
    }
    final long firstLong=DbUtil.getFirstLong(rs);
    elapsed=System.currentTimeMillis() - start;
    if (log.isDebugEnabled()) {
      log.debug("execution time: " + elapsed + "ms");
    }
    return firstLong;
  }
 catch (  SQLException sex) {
    throw new DbSqlException(this,"Count query failed",sex);
  }
 finally {
    DbUtil.close(rs);
    if (close) {
      close();
    }
  }
}
