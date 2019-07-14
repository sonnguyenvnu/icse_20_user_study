/** 
 * Encodes the given URI port with the given encoding.
 */
public static String encodePort(final String port,final String encoding){
  return encodeUriComponent(port,encoding,URIPart.PORT);
}
