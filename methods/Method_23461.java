public void strokeJoin(int join){
  if (!openShape) {
    PGraphics.showWarning(OUTSIDE_BEGIN_END_ERROR,"strokeJoin()");
    return;
  }
  strokeJoin=join;
}
