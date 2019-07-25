/** 
 * Creates a Favicon from an encoded PNG.
 * @param encodedString a base64 mime encoded PNG string
 * @return the created favicon
 * @deprecated Use #create(java.awt.image.BufferedImage) instead
 */
@Deprecated public static Favicon create(String encodedString){
  return new Favicon(encodedString);
}
