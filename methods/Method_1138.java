/** 
 * Creates a new RoundedBitmapDrawable from the given BitmapDrawable.
 * @param res resources to use for this drawable
 * @param bitmapDrawable bitmap drawable containing the bitmap to be used for this drawable
 * @return the RoundedBitmapDrawable that is created
 */
public static RoundedBitmapDrawable fromBitmapDrawable(Resources res,BitmapDrawable bitmapDrawable){
  return new RoundedBitmapDrawable(res,bitmapDrawable.getBitmap(),bitmapDrawable.getPaint());
}
