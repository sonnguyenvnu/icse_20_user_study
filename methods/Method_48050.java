private AstyanaxContext.Builder getContextBuilder(Configuration config,int maxConnsPerHost,String usedFor){
  final ConnectionPoolType poolType=ConnectionPoolType.valueOf(config.get(CONNECTION_POOL_TYPE));
  final NodeDiscoveryType discType=NodeDiscoveryType.valueOf(config.get(NODE_DISCOVERY_TYPE));
  final int maxConnections=config.get(MAX_CONNECTIONS);
  final int maxOperationsPerConnection=config.get(MAX_OPERATIONS_PER_CONNECTION);
  final int connectionTimeout=(int)connectionTimeoutMS.toMillis();
  ConnectionPoolConfigurationImpl cpool=new ConnectionPoolConfigurationImpl(usedFor + "JanusGraphConnectionPool").setPort(port).setMaxOperationsPerConnection(maxOperationsPerConnection).setMaxConnsPerHost(maxConnsPerHost).setRetryDelaySlice(retryDelaySlice).setRetryMaxDelaySlice(retryMaxDelaySlice).setRetrySuspendWindow(retrySuspendWindow).setSocketTimeout(connectionTimeout).setConnectTimeout(connectionTimeout).setSeeds(StringUtils.join(hostnames,","));
  if (null != retryBackoffStrategy) {
    cpool.setRetryBackoffStrategy(retryBackoffStrategy);
    log.debug("Custom RetryBackoffStrategy {}",cpool.getRetryBackoffStrategy());
  }
 else {
    log.debug("Default RetryBackoffStrategy {}",cpool.getRetryBackoffStrategy());
  }
  if (StringUtils.isNotBlank(localDatacenter)) {
    cpool.setLocalDatacenter(localDatacenter);
    log.debug("Set local datacenter: {}",cpool.getLocalDatacenter());
  }
  AstyanaxConfigurationImpl astyanaxConfiguration=new AstyanaxConfigurationImpl().setConnectionPoolType(poolType).setDiscoveryType(discType).setTargetCassandraVersion("1.2").setMaxThriftSize(thriftFrameSizeBytes);
  if (0 < maxConnections) {
    cpool.setMaxConns(maxConnections);
  }
  if (hasAuthentication()) {
    cpool.setAuthenticationCredentials(new SimpleAuthenticationCredentials(username,password));
  }
  if (config.get(SSL_ENABLED)) {
    cpool.setSSLConnectionContext(new SSLConnectionContext(config.get(SSL_TRUSTSTORE_LOCATION),config.get(SSL_TRUSTSTORE_PASSWORD)));
  }
  AstyanaxContext.Builder ctxBuilder=new AstyanaxContext.Builder();
  ctxBuilder.forCluster(clusterName).forKeyspace(keySpaceName).withAstyanaxConfiguration(astyanaxConfiguration).withConnectionPoolConfiguration(cpool).withConnectionPoolMonitor(new CountingConnectionPoolMonitor());
  if (config.has(HOST_SUPPLIER)) {
    String hostSupplier=config.get(HOST_SUPPLIER);
    final Supplier<List<Host>> supplier;
    if (hostSupplier != null) {
      try {
        supplier=(Supplier<List<Host>>)Class.forName(hostSupplier).newInstance();
        ctxBuilder.withHostSupplier(supplier);
      }
 catch (      Exception e) {
        log.warn("Problem with host supplier class " + hostSupplier + ", going to use default.",e);
      }
    }
  }
  return ctxBuilder;
}
