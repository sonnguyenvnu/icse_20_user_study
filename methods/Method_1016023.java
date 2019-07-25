/** 
 * Generates a normal form of the url, without the protocol part, except the skipped protocol part this is identical with toNormalform()
 * @see #toNormalform(boolean)
 * @param excludeAnchor, exclude anchor part
 * @param removeSessionID, exclude session id
 * @return example "www.host.com:8080/path/file.html"
 * @see #toNormalform(boolean,boolean)
 */
public String urlstub(final boolean excludeAnchor,final boolean removeSessionID){
  boolean defaultPort=false;
  if (this.protocol.equals("mailto")) {
    return this.userInfo + "@" + this.host;
  }
 else   if (isHTTP()) {
    if (this.port < 0 || this.port == 80) {
      defaultPort=true;
    }
  }
 else   if (isHTTPS()) {
    if (this.port < 0 || this.port == 443) {
      defaultPort=true;
    }
  }
 else   if (isFTP()) {
    if (this.port < 0 || this.port == 21) {
      defaultPort=true;
    }
  }
 else   if (isSMB()) {
    if (this.port < 0 || this.port == 445) {
      defaultPort=true;
    }
  }
 else   if (isFile()) {
    defaultPort=true;
  }
  String urlPath=this.getFile(excludeAnchor,removeSessionID);
  String h=getHost();
  final StringBuilder u=new StringBuilder(20 + urlPath.length() + ((h == null) ? 0 : h.length()));
  if (h != null) {
    if (this.userInfo != null && !(this.isFTP() && this.userInfo.startsWith("anonymous"))) {
      u.append(this.userInfo);
      u.append("@");
    }
    u.append(h.toLowerCase());
  }
  if (!defaultPort) {
    u.append(":");
    u.append(this.port);
  }
  u.append(urlPath);
  String result=u.toString();
  return result;
}
