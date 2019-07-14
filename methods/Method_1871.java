/** 
 * Decodes only the bounds of an image and returns its width and height or null if the size can't be determined
 * @param bytes the input byte array of the image
 * @return dimensions of the image
 */
public static @Nullable Pair<Integer,Integer> decodeDimensions(byte[] bytes){
  return decodeDimensions(new ByteArrayInputStream(bytes));
}
