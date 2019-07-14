/** 
 * Encodes the given URI path with the given encoding.
 */
public static String encodePath(final String path,final String encoding){
  return encodeUriComponent(path,encoding,URIPart.PATH);
}
