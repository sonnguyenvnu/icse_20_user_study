/** 
 * Maps point from view-absolute to image-relative coordinates. This takes into account the zoomable transformation.
 */
public PointF mapViewToImage(PointF viewPoint){
  float[] points=mTempValues;
  points[0]=viewPoint.x;
  points[1]=viewPoint.y;
  mActiveTransform.invert(mActiveTransformInverse);
  mActiveTransformInverse.mapPoints(points,0,points,0,1);
  mapAbsoluteToRelative(points,points,1);
  return new PointF(points[0],points[1]);
}
