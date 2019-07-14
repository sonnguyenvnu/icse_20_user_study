private static void PaintSegment(Point lastPoint,Point point,RenderState state){
  double distance=lastPoint.getDistanceTo(point);
  Point vector=point.substract(lastPoint);
  Point unitVector=new Point(1.0f,1.0f,0.0f);
  float vectorAngle=Math.abs(state.angle) > 0.0f ? state.angle : (float)Math.atan2(vector.y,vector.x);
  float brushWeight=state.baseWeight * state.scale;
  double step=Math.max(1.0f,state.spacing * brushWeight);
  if (distance > 0.0) {
    unitVector=vector.multiplyByScalar(1.0 / distance);
  }
  float boldenedAlpha=Math.min(1.0f,state.alpha * 1.15f);
  boolean boldenHead=lastPoint.edge;
  boolean boldenTail=point.edge;
  int count=(int)Math.ceil((distance - state.remainder) / step);
  int currentCount=state.getCount();
  state.appendValuesCount(count);
  state.setPosition(currentCount);
  Point start=lastPoint.add(unitVector.multiplyByScalar(state.remainder));
  boolean succeed=true;
  double f=state.remainder;
  for (; f <= distance; f+=step) {
    float alpha=boldenHead ? boldenedAlpha : state.alpha;
    succeed=state.addPoint(start.toPointF(),brushWeight,vectorAngle,alpha,-1);
    if (!succeed) {
      break;
    }
    start=start.add(unitVector.multiplyByScalar(step));
    boldenHead=false;
  }
  if (succeed && boldenTail) {
    state.appendValuesCount(1);
    state.addPoint(point.toPointF(),brushWeight,vectorAngle,boldenedAlpha,-1);
  }
  state.remainder=f - distance;
}
