@Override public String add(final JSONObject jsonObject) throws RepositoryException {
  final JdbcTransaction currentTransaction=TX.get();
  if (null == currentTransaction) {
    throw new RepositoryException("Invoking add() outside a transaction");
  }
  final Connection connection=getConnection();
  final List<Object> paramList=new ArrayList<>();
  final StringBuilder sql=new StringBuilder();
  String ret;
  try {
    if (Latkes.RuntimeDatabase.ORACLE == Latkes.getRuntimeDatabase()) {
      toOracleClobEmpty(jsonObject);
    }
    ret=buildAddSql(jsonObject,paramList,sql);
    JdbcUtil.executeSql(sql.toString(),paramList,connection,debug);
    JdbcUtil.fromOracleClobEmpty(jsonObject);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Add failed",e);
    throw new RepositoryException(e);
  }
  return ret;
}
