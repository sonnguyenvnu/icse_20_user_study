public void filter(DaoInterceptorChain chain) throws DaoException {
  DaoStatement statement=chain.getDaoStatement();
  if (statement != null) {
    NutDaoExecutor.printSQL(statement);
  }
  chain.doChain();
}
