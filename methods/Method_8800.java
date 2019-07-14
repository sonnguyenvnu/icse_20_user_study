private static void PaintStamp(Point point,RenderState state){
  float brushWeight=state.baseWeight * state.scale;
  PointF start=point.toPointF();
  float angle=Math.abs(state.angle) > 0.0f ? state.angle : 0.0f;
  float alpha=state.alpha;
  state.prepare();
  state.appendValuesCount(1);
  state.addPoint(start,brushWeight,angle,alpha,0);
}
