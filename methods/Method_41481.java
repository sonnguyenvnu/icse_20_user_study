private void populateProviderWithExtraProps(PoolingConnectionProvider cp,Properties props) throws Exception {
  Properties copyProps=new Properties();
  copyProps.putAll(props);
  copyProps.remove(PoolingConnectionProvider.DB_DRIVER);
  copyProps.remove(PoolingConnectionProvider.DB_URL);
  copyProps.remove(PoolingConnectionProvider.DB_USER);
  copyProps.remove(PoolingConnectionProvider.DB_PASSWORD);
  copyProps.remove(PoolingConnectionProvider.DB_MAX_CONNECTIONS);
  copyProps.remove(PoolingConnectionProvider.DB_VALIDATION_QUERY);
  copyProps.remove(PoolingConnectionProvider.POOLING_PROVIDER);
  if (cp instanceof C3p0PoolingConnectionProvider) {
    copyProps.remove(C3p0PoolingConnectionProvider.DB_MAX_CACHED_STATEMENTS_PER_CONNECTION);
    copyProps.remove(C3p0PoolingConnectionProvider.DB_VALIDATE_ON_CHECKOUT);
    copyProps.remove(C3p0PoolingConnectionProvider.DB_IDLE_VALIDATION_SECONDS);
    copyProps.remove(C3p0PoolingConnectionProvider.DB_DISCARD_IDLE_CONNECTIONS_SECONDS);
  }
  setBeanProps(cp.getDataSource(),copyProps);
}
