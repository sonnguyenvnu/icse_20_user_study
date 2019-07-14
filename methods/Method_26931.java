/** 
 * Creates a Bitmap of the given view, using the Matrix matrix to transform to the local coordinates. <code>matrix</code> will be modified during the bitmap creation. <p>If the bitmap is large, it will be scaled uniformly down to at most 1MB size.</p>
 * @param view The view to create a bitmap for.
 * @param matrix The matrix converting the view local coordinates to the coordinates thatthe bitmap will be displayed in. <code>matrix</code> will be modified before returning.
 * @param bounds The bounds of the bitmap in the destination coordinate system (where theview should be presented. Typically, this is matrix.mapRect(viewBounds);
 * @return A bitmap of the given view or null if bounds has no width or height.
 */
@Nullable public static Bitmap createViewBitmap(@NonNull View view,@NonNull Matrix matrix,@NonNull RectF bounds){
  Bitmap bitmap=null;
  int bitmapWidth=Math.round(bounds.width());
  int bitmapHeight=Math.round(bounds.height());
  if (bitmapWidth > 0 && bitmapHeight > 0) {
    float scale=Math.min(1f,((float)MAX_IMAGE_SIZE) / (bitmapWidth * bitmapHeight));
    bitmapWidth*=scale;
    bitmapHeight*=scale;
    matrix.postTranslate(-bounds.left,-bounds.top);
    matrix.postScale(scale,scale);
    try {
      bitmap=Bitmap.createBitmap(bitmapWidth,bitmapHeight,Bitmap.Config.ARGB_8888);
      Canvas canvas=new Canvas(bitmap);
      canvas.concat(matrix);
      view.draw(canvas);
    }
 catch (    OutOfMemoryError e) {
    }
  }
  return bitmap;
}
