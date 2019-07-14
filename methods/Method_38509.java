/** 
 * Returns just host url, without path and query.
 */
public String hostUrl(){
  StringBand url=new StringBand(8);
  if (protocol != null) {
    url.append(protocol);
    url.append("://");
  }
  if (host != null) {
    url.append(host);
  }
  if (port != Defaults.DEFAULT_PORT) {
    url.append(':');
    url.append(port);
  }
  return url.toString();
}
