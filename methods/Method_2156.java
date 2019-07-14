private float calcScale(PointF currentViewPoint){
  float dy=(currentViewPoint.y - mDoubleTapViewPoint.y);
  float t=1 + Math.abs(dy) * 0.001f;
  return (dy < 0) ? mDoubleTapScale / t : mDoubleTapScale * t;
}
