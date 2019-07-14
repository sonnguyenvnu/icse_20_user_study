/** 
 * Zooms to the desired scale and positions the image so that the given image point corresponds to the given view point. <p>If this method is called while an animation or gesture is already in progress, the current animation or gesture will be stopped first.
 * @param scale desired scale, will be limited to {min, max} scale factor
 * @param imagePoint 2D point in image's relative coordinate system (i.e. 0 <= x, y <= 1)
 * @param viewPoint 2D point in view's absolute coordinate system
 */
@Override public void zoomToPoint(float scale,PointF imagePoint,PointF viewPoint){
  zoomToPoint(scale,imagePoint,viewPoint,LIMIT_ALL,0,null);
}
