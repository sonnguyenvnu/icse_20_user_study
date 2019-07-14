@Override protected void onDrag(MotionEvent event,float delta){
  if (delta > 0) {
    int oldScroll=getScroll();
    scrollBy((int)-delta);
    delta+=getScroll() - oldScroll;
    offsetBy((int)delta);
  }
 else {
    int oldOffset=getOffset();
    offsetBy((int)delta);
    delta-=getOffset() - oldOffset;
    int oldScroll=getScroll();
    scrollBy((int)-delta);
    delta+=getScroll() - oldScroll;
    if (delta < 0) {
      pullEdgeEffectBottom(event,delta);
    }
  }
}
