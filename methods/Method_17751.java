/** 
 * Sets the bounds on the given view if the view doesn't already have those bounds (or if 'force' is supplied).
 */
public static void applyBoundsToView(View view,int left,int top,int right,int bottom,boolean force){
  final int width=right - left;
  final int height=bottom - top;
  if (force || view.getMeasuredHeight() != height || view.getMeasuredWidth() != width) {
    view.measure(makeMeasureSpec(width,View.MeasureSpec.EXACTLY),makeMeasureSpec(height,View.MeasureSpec.EXACTLY));
  }
  if (force || view.getLeft() != left || view.getTop() != top || view.getRight() != right || view.getBottom() != bottom) {
    view.layout(left,top,right,bottom);
  }
}
