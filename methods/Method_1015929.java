@Override public void remove(final String id) throws RepositoryException {
  if (StringUtils.isBlank(id)) {
    return;
  }
  final JdbcTransaction currentTransaction=TX.get();
  if (null == currentTransaction) {
    throw new RepositoryException("Invoking remove() outside a transaction");
  }
  final StringBuilder sql=new StringBuilder();
  final Connection connection=getConnection();
  try {
    sql.append("DELETE FROM ").append(getName()).append(" WHERE ").append(JdbcRepositories.getDefaultKeyName()).append(" = '").append(id).append("'");
    JdbcUtil.executeSql(sql.toString(),connection,debug);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Remove failed",e);
    throw new RepositoryException(e);
  }
}
