/** 
 * Select an appropriate character encoding to be used, based on the characteristics of the current request and/or filter initialization parameters.  If no character encoding should be set, return <code>null</code>. <p> The default implementation unconditionally returns the value configured by the <strong>encoding</strong> initialization parameter for this filter.
 * @param request The servlet request we are processing
 */
protected String selectEncoding(final ServletRequest request){
  return this.encoding;
}
