protected void onDrag(MotionEvent event,float delta){
  int oldScroll=mScroll;
  scrollBy((int)-delta);
  delta+=mScroll - oldScroll;
  if (delta < 0) {
    pullEdgeEffectBottom(event,delta);
  }
}
