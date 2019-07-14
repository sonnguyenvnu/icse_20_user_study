/** 
 * Prepares request on creation. By default, it just adds "Connection: Close" header.
 */
protected void initRequest(){
  connectionKeepAlive(false);
}
