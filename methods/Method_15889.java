private SqlSession openSessionFromConnection(ExecutorType execType,Connection connection){
  try {
    boolean autoCommit;
    try {
      autoCommit=connection.getAutoCommit();
    }
 catch (    SQLException e) {
      autoCommit=true;
    }
    final Environment environment=configuration.getEnvironment();
    final TransactionFactory transactionFactory=getTransactionFactoryFromEnvironment(environment);
    final Transaction tx=transactionFactory.newTransaction(connection);
    final Executor executor=configuration.newExecutor(tx,execType);
    return new DefaultSqlSession(configuration,executor,autoCommit);
  }
 catch (  Exception e) {
    throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e,e);
  }
 finally {
    ErrorContext.instance().reset();
  }
}
