/** 
 * bitmap?drawable
 * @param res    resources??
 * @param bitmap bitmap??
 * @return drawable??
 */
public static Drawable bitmap2Drawable(Resources res,Bitmap bitmap){
  return new BitmapDrawable(res,bitmap);
}
