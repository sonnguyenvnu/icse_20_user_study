/** 
 * Creates an SSLSocketFactory for HTTPS. Pass a loaded KeyStore and an array of loaded KeyManagers. These objects must properly loaded/initialized by the caller.
 */
public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore,KeyManager[] keyManagers) throws IOException {
  SSLServerSocketFactory res=null;
  try {
    TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    trustManagerFactory.init(loadedKeyStore);
    SSLContext ctx=SSLContext.getInstance("TLS");
    ctx.init(keyManagers,trustManagerFactory.getTrustManagers(),null);
    res=ctx.getServerSocketFactory();
  }
 catch (  Exception e) {
    throw new IOException(e.getMessage());
  }
  return res;
}
