@Override public PointF evaluate(float fraction,PointF startValue,PointF endValue){
  return getBezierPoint(startValue,endValue,control,fraction);
}
