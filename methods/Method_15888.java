@SuppressWarnings("all") private SqlSession openSessionFromDataSource(ExecutorType execType,TransactionIsolationLevel level,boolean autoCommit){
  Transaction tx=null;
  try {
    final Environment environment=getConfiguration().getEnvironment();
    final TransactionFactory transactionFactory=getTransactionFactoryFromEnvironment(environment);
    DataSource ds=DataSourceHolder.currentDataSource().getNative();
    if (ds == null) {
      ds=environment.getDataSource();
    }
    tx=transactionFactory.newTransaction(ds,level,autoCommit);
    final Executor executor=getConfiguration().newExecutor(tx,execType);
    return new DefaultSqlSession(getConfiguration(),executor,autoCommit);
  }
 catch (  Exception e) {
    closeTransaction(tx);
    throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e,e);
  }
 finally {
    ErrorContext.instance().reset();
  }
}
