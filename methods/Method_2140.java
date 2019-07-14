/** 
 * Zooms to the desired scale and positions the image so that the given image point corresponds to the given view point.
 * @param scale desired scale, will be limited to {min, max} scale factor
 * @param imagePoint 2D point in image's relative coordinate system (i.e. 0 <= x, y <= 1)
 * @param viewPoint 2D point in view's absolute coordinate system
 */
public void zoomToPoint(float scale,PointF imagePoint,PointF viewPoint){
  FLog.v(TAG,"zoomToPoint");
  calculateZoomToPointTransform(mActiveTransform,scale,imagePoint,viewPoint,LIMIT_ALL);
  onTransformChanged();
}
