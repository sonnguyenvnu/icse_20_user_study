/** 
 * Checks whether underlying platform supports extended WebPs
 */
private static boolean isExtendedWebpSupported(){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
    return false;
  }
  if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1) {
    byte[] decodedBytes=Base64.decode(VP8X_WEBP_BASE64,Base64.DEFAULT);
    BitmapFactory.Options opts=new BitmapFactory.Options();
    opts.inJustDecodeBounds=true;
    BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.length,opts);
    if (opts.outHeight != 1 || opts.outWidth != 1) {
      return false;
    }
  }
  return true;
}
