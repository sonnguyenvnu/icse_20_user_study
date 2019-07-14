/** 
 * Create an instance from a URI.
 * @param uri image URI.
 * @return an {@link ImageSource} instance.
 */
@NonNull public static ImageSource uri(@NonNull Uri uri){
  if (uri == null) {
    throw new NullPointerException("Uri must not be null");
  }
  return new ImageSource(uri);
}
