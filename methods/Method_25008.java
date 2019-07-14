/** 
 * @return {@code true} if connection is to be closed after this Responsehas been sent.
 */
public boolean isCloseConnection(){
  return "close".equals(getHeader("connection"));
}
