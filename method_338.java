@Override public <T,E extends Throwable>int _XXXXX_(String insertSql,Collection<T> entities,ThrowableConsumer2<PreparedStatement,T,E> mapper) throws E, SQLException {
  Connection connection=null;
  PreparedStatement statement=null;
  try {
    connection=dataSource.getConnection();
    statement=connection.prepareStatement(insertSql);
    connection.setAutoCommit(false);
    for (    T entity : entities) {
      mapper.accept(statement,entity);
      statement.addBatch();
    }
    int[] num=statement.executeBatch();
    connection.commit();
    int sum=0;
    for (    int i : num) {
      sum+=i;
    }
    return sum;
  }
 catch (  SQLException ex) {
    LOGGER.error("Error to insert batch: {}",insertSql,ex);
    throw ex;
  }
 finally {
    if (statement != null) {
      try {
        statement.close();
      }
 catch (      SQLException e) {
        LOGGER.error(e.getMessage(),e);
      }
    }
    if (connection != null) {
      try {
        connection.close();
      }
 catch (      SQLException e) {
        LOGGER.error(e.getMessage(),e);
      }
    }
  }
}