/** 
 * Call before start() to serve over HTTPS instead of HTTP
 */
public void makeSecure(SSLServerSocketFactory sslServerSocketFactory,String[] sslProtocols){
  this.serverSocketFactory=new SecureServerSocketFactory(sslServerSocketFactory,sslProtocols);
}
