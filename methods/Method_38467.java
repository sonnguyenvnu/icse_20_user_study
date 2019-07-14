/** 
 * Defines just content media type. Setting this value to <code>null</code> will not have any effects.
 */
public T mediaType(final String mediaType){
  contentType(mediaType,null);
  return _this();
}
