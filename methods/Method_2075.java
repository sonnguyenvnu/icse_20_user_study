/** 
 * Create a new decoder that can decode  {@link #IMAGE_FORMAT_COLOR} images.
 * @return the decoder
 */
public static ImageDecoder createDecoder(){
  return new ColorDecoder();
}
