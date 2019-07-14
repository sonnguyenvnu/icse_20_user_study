/** 
 * Creates a new request builder instance. The setting will be done according to the source type.
 * @param uri the uri to fetch
 * @return a new request builder instance
 */
public static ImageRequestBuilder newBuilderWithSource(Uri uri){
  return new ImageRequestBuilder().setSource(uri);
}
