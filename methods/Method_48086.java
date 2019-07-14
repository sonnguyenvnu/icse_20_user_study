/** 
 * Create a Cassandra-Thrift connection, but do not attempt to set a keyspace on the connection.
 * @return A CTConnection ready to talk to a Cassandra cluster
 * @throws TTransportException on any Thrift transport failure
 */
public CTConnection makeRawConnection() throws TTransportException {
  final Config cfg=cfgRef.get();
  String hostname=cfg.getRandomHost();
  log.debug("Creating TSocket({}, {}, {}, {}, {})",hostname,cfg.port,cfg.username,cfg.password,cfg.timeoutMS);
  TSocket socket;
  if (null != cfg.sslTruststoreLocation && !cfg.sslTruststoreLocation.isEmpty()) {
    TSSLTransportFactory.TSSLTransportParameters params=new TSSLTransportFactory.TSSLTransportParameters(){
{
        setTrustStore(cfg.sslTruststoreLocation,cfg.sslTruststorePassword);
      }
    }
;
    socket=TSSLTransportFactory.getClientSocket(hostname,cfg.port,cfg.timeoutMS,params);
  }
 else {
    socket=new TSocket(hostname,cfg.port,cfg.timeoutMS);
  }
  TTransport transport=new TFramedTransport(socket,cfg.frameSize);
  log.trace("Created transport {}",transport);
  TBinaryProtocol protocol=new TBinaryProtocol(transport);
  Cassandra.Client client=new Cassandra.Client(protocol);
  if (!transport.isOpen()) {
    transport.open();
  }
  if (cfg.username != null) {
    Map<String,String> credentials=new HashMap<String,String>(){
{
        put(PasswordAuthenticator.USERNAME_KEY,cfg.username);
        put(PasswordAuthenticator.PASSWORD_KEY,cfg.password);
      }
    }
;
    try {
      client.login(new AuthenticationRequest(credentials));
    }
 catch (    Exception e) {
      throw new TTransportException(e);
    }
  }
  return new CTConnection(transport,client,cfg);
}
