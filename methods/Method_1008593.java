/** 
 * The path part of the URI (without the query string), decoded.
 */
public final String path(){
  return RestUtils.decodeComponent(rawPath());
}
