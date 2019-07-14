/** 
 * byteArr?drawable
 * @param res   resources??
 * @param bytes ????
 * @return drawable??
 */
public static Drawable bytes2Drawable(Resources res,byte[] bytes){
  Bitmap bitmap=bytes2Bitmap(bytes);
  Drawable drawable=bitmap2Drawable(res,bitmap);
  return drawable;
}
