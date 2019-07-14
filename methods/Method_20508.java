/** 
 * Returns the source point at the center of the view.
 * @return the source coordinates current at the center of the view.
 */
@Nullable public final PointF getCenter(){
  int mX=getWidth() / 2;
  int mY=getHeight() / 2;
  return viewToSourceCoord(mX,mY);
}
