/** 
 * Get the clickable span that's close to where the view was clicked.
 * @param x x-position of the click
 * @param y y-position of the click
 * @return a clickable span that's close the click position,or:  {@code null} if no clickable span was close to the click,or if a link was directly clicked or if more than one clickable span was in proximity to the click.
 */
@Nullable private ClickableSpan getClickableSpanInProximityToClick(float x,float y,float tapRadius){
  final Region touchAreaRegion=new Region();
  final Region clipBoundsRegion=new Region();
  if (mTouchAreaPath == null) {
    mTouchAreaPath=new Path();
  }
  clipBoundsRegion.set(0,0,LayoutMeasureUtil.getWidth(mLayout),LayoutMeasureUtil.getHeight(mLayout));
  mTouchAreaPath.reset();
  mTouchAreaPath.addCircle(x,y,tapRadius,Path.Direction.CW);
  touchAreaRegion.setPath(mTouchAreaPath,clipBoundsRegion);
  ClickableSpan result=null;
  for (  ClickableSpan span : mClickableSpans) {
    if (!isClickCloseToSpan(span,(Spanned)mText,mLayout,touchAreaRegion,clipBoundsRegion)) {
      continue;
    }
    if (result != null) {
      return null;
    }
    result=span;
  }
  return result;
}
