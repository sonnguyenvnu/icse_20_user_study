Cluster initializeCluster() throws PermanentBackendException {
  final Configuration configuration=getStorageConfig();
  final List<InetSocketAddress> contactPoints;
  try {
    contactPoints=Array.of(this.hostnames).map(hostName -> hostName.split(":")).map(array -> Tuple.of(array[0],array.length == 2 ? Integer.parseInt(array[1]) : this.port)).map(tuple -> new InetSocketAddress(tuple._1,tuple._2)).toJavaList();
  }
 catch (  SecurityException|ArrayIndexOutOfBoundsException|NumberFormatException e) {
    throw new PermanentBackendException("Error initialising cluster contact points",e);
  }
  final Builder builder=Cluster.builder().addContactPointsWithPorts(contactPoints).withClusterName(configuration.get(CLUSTER_NAME));
  if (configuration.get(PROTOCOL_VERSION) != 0) {
    builder.withProtocolVersion(ProtocolVersion.fromInt(configuration.get(PROTOCOL_VERSION)));
  }
  if (configuration.has(AUTH_USERNAME) && configuration.has(AUTH_PASSWORD)) {
    builder.withCredentials(configuration.get(AUTH_USERNAME),configuration.get(AUTH_PASSWORD));
  }
  if (configuration.has(LOCAL_DATACENTER)) {
    builder.withLoadBalancingPolicy(new TokenAwarePolicy(DCAwareRoundRobinPolicy.builder().withLocalDc(configuration.get(LOCAL_DATACENTER)).build()));
  }
  if (configuration.get(SSL_ENABLED)) {
    try {
      final TrustManager[] trustManagers;
      try (final FileInputStream keyStoreStream=new FileInputStream(configuration.get(SSL_TRUSTSTORE_LOCATION))){
        final KeyStore keystore=KeyStore.getInstance("jks");
        keystore.load(keyStoreStream,configuration.get(SSL_TRUSTSTORE_PASSWORD).toCharArray());
        final TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keystore);
        trustManagers=trustManagerFactory.getTrustManagers();
      }
       final SSLContext sslContext=SSLContext.getInstance("TLS");
      sslContext.init(null,trustManagers,null);
      final JdkSSLOptions sslOptions=JdkSSLOptions.builder().withSSLContext(sslContext).build();
      builder.withSSL(sslOptions);
    }
 catch (    NoSuchAlgorithmException|CertificateException|IOException|KeyStoreException|KeyManagementException e) {
      throw new PermanentBackendException("Error initialising SSL connection properties",e);
    }
  }
  PoolingOptions poolingOptions=new PoolingOptions();
  poolingOptions.setMaxRequestsPerConnection(HostDistance.LOCAL,configuration.get(LOCAL_MAX_REQUESTS_PER_CONNECTION)).setMaxRequestsPerConnection(HostDistance.REMOTE,configuration.get(REMOTE_MAX_REQUESTS_PER_CONNECTION));
  poolingOptions.setConnectionsPerHost(HostDistance.LOCAL,configuration.get(LOCAL_CORE_CONNECTIONS_PER_HOST),configuration.get(LOCAL_MAX_CONNECTIONS_PER_HOST)).setConnectionsPerHost(HostDistance.REMOTE,configuration.get(REMOTE_CORE_CONNECTIONS_PER_HOST),configuration.get(REMOTE_MAX_CONNECTIONS_PER_HOST));
  return builder.withPoolingOptions(poolingOptions).build();
}
