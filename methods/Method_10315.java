/** 
 * Returns a SSlSocketFactory which trusts all certificates
 * @return SSLSocketFactory
 */
public static SSLSocketFactory getFixedSocketFactory(){
  SSLSocketFactory socketFactory;
  try {
    socketFactory=new MySSLSocketFactory(getKeystore());
    socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
  }
 catch (  Throwable t) {
    t.printStackTrace();
    socketFactory=SSLSocketFactory.getSocketFactory();
  }
  return socketFactory;
}
