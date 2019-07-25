/** 
 * @param filename source image file url
 * @param source image content as bytes
 * @return an Image instance parsed from image content bytes, or null if no parser can handle image format or an error occured
 */
public static final Image parse(final String filename,final byte[] source){
  BufferedImage image=null;
  try {
    image=ImageIO.read(new ByteArrayInputStream(source));
  }
 catch (  IOException e) {
    Data.logger.debug("IMAGEPARSER.parse : could not parse image " + filename,e);
  }
  if (image == null) {
    Data.logger.debug("IMAGEPARSER.parse : ImageIO failed for " + filename);
    return null;
  }
  return image;
}
