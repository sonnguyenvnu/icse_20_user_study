/** 
 * Create an instance from a URI.
 * @param uri image URI.
 */
public static ImageSource uri(Uri uri){
  if (uri == null) {
    throw new NullPointerException("Uri must not be null");
  }
  return new ImageSource(uri);
}
