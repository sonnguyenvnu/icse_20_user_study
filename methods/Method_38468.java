/** 
 * Returns <code>true</code> if connection is persistent. If "Connection" header does not exist, returns <code>true</code> for HTTP 1.1 and <code>false</code> for HTTP 1.0. If "Connection" header exist, checks if it is equal to "Close". <p> In HTTP 1.1, all connections are considered persistent unless declared otherwise. Under HTTP 1.0, there is no official specification for how keepalive operates.
 */
public boolean isConnectionPersistent(){
  String connection=header(HEADER_CONNECTION);
  if (connection == null) {
    return !httpVersion.equalsIgnoreCase(HTTP_1_0);
  }
  return !connection.equalsIgnoreCase(HEADER_CLOSE);
}
