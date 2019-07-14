/** 
 * Creates a custom  {@link SSLSocketFactory} that disallows the use of a set of protocols and/orciphers, no matter the current default configuration.
 * @param disabledProtocolsAndCiphers list of protocol or cipher names to disallow
 * @return An {@link SSLSocketFactory} that will never use the passed protocols or ciphers
 */
public static SSLSocketFactory createRestrictedSSLSocketFactory(String... disabledProtocolsAndCiphers){
  final Set<String> disabled=new CopyOnWriteArraySet<>(Arrays.asList(disabledProtocolsAndCiphers));
  return new SSLSocketFactory(){
    private String[] filter(    String[] original,    String[] supported) throws IOException {
      Set<String> filtered=new CopyOnWriteArraySet<>(Arrays.asList(original));
      filtered.removeAll(disabled);
      if (filtered.isEmpty()) {
        filtered.addAll(Arrays.asList(supported));
        filtered.removeAll(disabled);
      }
      if (filtered.isEmpty())       throw new IOException("No supported SSL attributed enabled.  " + Arrays.toString(original) + " provided, " + disabled.toString() + " disabled, " + Arrays.toString(supported) + " supported, result: " + filtered.toString());
      return filtered.toArray(new String[filtered.size()]);
    }
    private SSLSocket fixupSocket(    Socket socket) throws IOException {
      SSLSocket sslSocket=(SSLSocket)socket;
      sslSocket.setEnabledProtocols(filter(sslSocket.getEnabledProtocols(),sslSocket.getSupportedProtocols()));
      sslSocket.setEnabledCipherSuites(filter(sslSocket.getEnabledCipherSuites(),sslSocket.getSupportedCipherSuites()));
      return sslSocket;
    }
    private SSLSocketFactory getDefaultFactory(){
      return (SSLSocketFactory)SSLSocketFactory.getDefault();
    }
    @Override public String[] getDefaultCipherSuites(){
      return getDefaultFactory().getDefaultCipherSuites();
    }
    @Override public String[] getSupportedCipherSuites(){
      return getDefaultFactory().getSupportedCipherSuites();
    }
    @Override public Socket createSocket(    Socket s,    String host,    int port,    boolean autoClose) throws IOException {
      return fixupSocket(getDefaultFactory().createSocket(s,host,port,autoClose));
    }
    @Override public Socket createSocket(    String host,    int port) throws IOException {
      return fixupSocket(getDefaultFactory().createSocket(host,port));
    }
    @Override public Socket createSocket(    String host,    int port,    InetAddress localHost,    int localPort) throws IOException {
      return fixupSocket(getDefaultFactory().createSocket(host,port,localHost,localPort));
    }
    @Override public Socket createSocket(    InetAddress host,    int port) throws IOException {
      return fixupSocket(getDefaultFactory().createSocket(host,port));
    }
    @Override public Socket createSocket(    InetAddress address,    int port,    InetAddress localAddress,    int localPort) throws IOException {
      return fixupSocket(getDefaultFactory().createSocket(address,port,localAddress,localPort));
    }
  }
;
}
