/** 
 * Sets 'Host' header from current host and port.
 */
public HttpRequest setHostHeader(){
  String hostPort=this.host;
  if (port != Defaults.DEFAULT_PORT) {
    hostPort+=StringPool.COLON + port;
  }
  headerOverwrite(HEADER_HOST,hostPort);
  return this;
}
