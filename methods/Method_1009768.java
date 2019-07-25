public boolean contains(@NonNull float[] point){
  Matrix tempMatrix=new Matrix();
  tempMatrix.setRotate(-getCurrentAngle());
  getBoundPoints(boundPoints);
  getMappedPoints(mappedBounds,boundPoints);
  tempMatrix.mapPoints(unrotatedWrapperCorner,mappedBounds);
  tempMatrix.mapPoints(unrotatedPoint,point);
  StickerUtils.trapToRect(trappedRect,unrotatedWrapperCorner);
  return trappedRect.contains(unrotatedPoint[0],unrotatedPoint[1]);
}
