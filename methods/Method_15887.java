@Override public SqlSession openSession(Connection connection){
  return openSessionFromConnection(configuration.getDefaultExecutorType(),connection);
}
