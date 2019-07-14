@Override public void strokeJoin(int join){
  super.strokeJoin(join);
  if (strokeJoin == MITER) {
    context.setLineJoin(StrokeLineJoin.MITER);
  }
 else   if (strokeJoin == ROUND) {
    context.setLineJoin(StrokeLineJoin.ROUND);
  }
 else {
    context.setLineJoin(StrokeLineJoin.BEVEL);
  }
}
