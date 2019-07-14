private SSLContext createServerContext(){
  InputStream is=this.getKeyStore();
  try {
    KeyStore keyStore=KeyStore.getInstance("JKS");
    keyStore.load(is,this.getKeyStorePassword());
    KeyManagerFactory factory=KeyManagerFactory.getInstance(getAlgorithm());
    factory.init(keyStore,this.getCertPassword());
    SSLContext serverContext=SSLContext.getInstance(PROTOCOL);
    serverContext.init(factory.getKeyManagers(),null,null);
    return serverContext;
  }
 catch (  Exception e) {
    throw new MocoException("Failed to initialize the server-side SSLContext",e);
  }
 finally {
    Closeables.closeQuietly(is);
  }
}
