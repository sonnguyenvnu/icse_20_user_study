/** 
 * Decodes the bounds of an image from its Uri and returns a pair of the dimensions
 * @param uri the Uri of the image
 * @return dimensions of the image
 */
public static @Nullable Pair<Integer,Integer> decodeDimensions(Uri uri){
  Preconditions.checkNotNull(uri);
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  BitmapFactory.decodeFile(uri.getPath(),options);
  return (options.outWidth == -1 || options.outHeight == -1) ? null : new Pair<>(options.outWidth,options.outHeight);
}
