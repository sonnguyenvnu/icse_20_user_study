/** 
 * Closes the encoded image handling null.
 * @param encodedImage the encoded image to close.
 */
public static void closeSafely(@Nullable EncodedImage encodedImage){
  if (encodedImage != null) {
    encodedImage.close();
  }
}
