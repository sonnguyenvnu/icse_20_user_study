public static Drawable bytes2Drawable(byte[] bytes){
  Bitmap bitmap=bytes2Bitmap(bytes);
  Drawable drawable=bitmap2Drawable(bitmap);
  return drawable;
}
