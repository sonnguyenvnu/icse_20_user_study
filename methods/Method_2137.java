/** 
 * Maps point from image-relative to view-absolute coordinates. This takes into account the zoomable transformation.
 */
public PointF mapImageToView(PointF imagePoint){
  float[] points=mTempValues;
  points[0]=imagePoint.x;
  points[1]=imagePoint.y;
  mapRelativeToAbsolute(points,points,1);
  mActiveTransform.mapPoints(points,0,points,0,1);
  return new PointF(points[0],points[1]);
}
