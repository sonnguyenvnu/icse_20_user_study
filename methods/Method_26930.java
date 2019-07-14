/** 
 * Get a copy of bitmap of given drawable, return null if intrinsic size is zero
 */
@Nullable public static Bitmap createDrawableBitmap(@NonNull Drawable drawable){
  int width=drawable.getIntrinsicWidth();
  int height=drawable.getIntrinsicHeight();
  if (width <= 0 || height <= 0) {
    return null;
  }
  float scale=Math.min(1f,((float)MAX_IMAGE_SIZE) / (width * height));
  if (drawable instanceof BitmapDrawable && scale == 1f) {
    return ((BitmapDrawable)drawable).getBitmap();
  }
  int bitmapWidth=(int)(width * scale);
  int bitmapHeight=(int)(height * scale);
  Bitmap bitmap=Bitmap.createBitmap(bitmapWidth,bitmapHeight,Bitmap.Config.ARGB_8888);
  Canvas canvas=new Canvas(bitmap);
  Rect existingBounds=drawable.getBounds();
  int left=existingBounds.left;
  int top=existingBounds.top;
  int right=existingBounds.right;
  int bottom=existingBounds.bottom;
  drawable.setBounds(0,0,bitmapWidth,bitmapHeight);
  drawable.draw(canvas);
  drawable.setBounds(left,top,right,bottom);
  return bitmap;
}
