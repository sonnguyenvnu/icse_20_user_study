/** 
 * Determines if buffering should be used for some HTTP status code. By default returns <code>true</code> for status code 200 and, optionally, for error pages (status code  {@literal >=} 400).
 */
public boolean decorateStatusCode(final int statusCode){
  return (statusCode == 200) || (decorateErrorPages && statusCode >= 400);
}
