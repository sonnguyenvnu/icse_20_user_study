/** 
 * Creates a View using the bitmap copy of <code>view</code>. If <code>view</code> is large, the copy will use a scaled bitmap of the given view.
 * @param sceneRoot The ViewGroup in which the view copy will be displayed.
 * @param view The view to create a copy of.
 * @param parent The parent of view
 */
@NonNull public static View copyViewImage(@NonNull ViewGroup sceneRoot,@NonNull View view,@NonNull View parent){
  Matrix matrix=new Matrix();
  matrix.setTranslate(-parent.getScrollX(),-parent.getScrollY());
  ViewUtils.transformMatrixToGlobal(view,matrix);
  ViewUtils.transformMatrixToLocal(sceneRoot,matrix);
  RectF bounds=new RectF(0,0,view.getWidth(),view.getHeight());
  matrix.mapRect(bounds);
  int left=Math.round(bounds.left);
  int top=Math.round(bounds.top);
  int right=Math.round(bounds.right);
  int bottom=Math.round(bounds.bottom);
  ImageView copy=new ImageView(view.getContext());
  copy.setScaleType(ImageView.ScaleType.CENTER_CROP);
  Bitmap bitmap=createViewBitmap(view,matrix,bounds);
  if (bitmap != null) {
    copy.setImageBitmap(bitmap);
  }
  int widthSpec=View.MeasureSpec.makeMeasureSpec(right - left,View.MeasureSpec.EXACTLY);
  int heightSpec=View.MeasureSpec.makeMeasureSpec(bottom - top,View.MeasureSpec.EXACTLY);
  copy.measure(widthSpec,heightSpec);
  copy.layout(left,top,right,bottom);
  return copy;
}
