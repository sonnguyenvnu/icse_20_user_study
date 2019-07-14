/** 
 * Determines if buffering should be used for some HTTP status code. By default returns <code>true</code> only for status code <b>200</b>.
 */
protected boolean bufferStatusCode(final int statusCode){
  return statusCode == 200;
}
