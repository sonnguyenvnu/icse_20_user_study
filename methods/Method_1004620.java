/** 
 * Important note: this function returns correct results only if the Polygon has been drawn before,  and if the MapView positioning has not changed. 
 * @param event
 * @return true if the Polygon contains the event position. 
 */
public boolean contains(MotionEvent event){
  if (mPath.isEmpty())   return false;
  RectF bounds=new RectF();
  mPath.computeBounds(bounds,true);
  Region region=new Region();
  region.setPath(mPath,new Region((int)bounds.left,(int)bounds.top,(int)(bounds.right),(int)(bounds.bottom)));
  return region.contains((int)event.getX(),(int)event.getY());
}
