/** 
 * Create a new image format checker for  {@link #IMAGE_FORMAT_COLOR}.
 * @return the image format checker
 */
public static ImageFormat.FormatChecker createFormatChecker(){
  return new ColorFormatChecker();
}
