private void initialize(int cachePercent) throws BackendException {
  try {
    EnvironmentConfig envConfig=new EnvironmentConfig();
    envConfig.setAllowCreate(true);
    envConfig.setTransactional(transactional);
    envConfig.setCachePercent(cachePercent);
    if (batchLoading) {
      envConfig.setConfigParam(EnvironmentConfig.ENV_RUN_CHECKPOINTER,"false");
      envConfig.setConfigParam(EnvironmentConfig.ENV_RUN_CLEANER,"false");
    }
    environment=new Environment(directory,envConfig);
  }
 catch (  DatabaseException e) {
    throw new PermanentBackendException("Error during BerkeleyJE initialization: ",e);
  }
}
