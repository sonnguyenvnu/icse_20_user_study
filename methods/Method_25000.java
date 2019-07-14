/** 
 * Creates an SSLSocketFactory for HTTPS. Pass a loaded KeyStore and a loaded KeyManagerFactory. These objects must properly loaded/initialized by the caller.
 */
public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore loadedKeyStore,KeyManagerFactory loadedKeyFactory) throws IOException {
  try {
    return makeSSLSocketFactory(loadedKeyStore,loadedKeyFactory.getKeyManagers());
  }
 catch (  Exception e) {
    throw new IOException(e.getMessage());
  }
}
