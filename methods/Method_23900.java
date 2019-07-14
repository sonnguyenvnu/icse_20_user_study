@Override public void strokeCap(int cap){
  super.strokeCap(cap);
  if (strokeCap == ROUND) {
    context.setLineCap(StrokeLineCap.ROUND);
  }
 else   if (strokeCap == PROJECT) {
    context.setLineCap(StrokeLineCap.SQUARE);
  }
 else {
    context.setLineCap(StrokeLineCap.BUTT);
  }
}
