/** 
 * Defines just content type charset. Setting this value to <code>null</code> will remove the charset information from the header.
 */
public T charset(final String charset){
  this.charset=null;
  contentType(null,charset);
  return _this();
}
