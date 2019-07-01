/** 
 * Returns true if the request contains conditions that save the server from sending a response that the client has locally. When the caller adds conditions, this cache won't participate in the request.
 */
public boolean _XXXXX_(){
  return ifModifiedSince != null || ifNoneMatch != null;
}