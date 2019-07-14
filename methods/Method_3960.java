@Override public boolean onGenericMotionEvent(MotionEvent event){
  if (mLayout == null) {
    return false;
  }
  if (mLayoutSuppressed) {
    return false;
  }
  if (event.getAction() == MotionEvent.ACTION_SCROLL) {
    final float vScroll, hScroll;
    if ((event.getSource() & InputDeviceCompat.SOURCE_CLASS_POINTER) != 0) {
      if (mLayout.canScrollVertically()) {
        vScroll=-event.getAxisValue(MotionEvent.AXIS_VSCROLL);
      }
 else {
        vScroll=0f;
      }
      if (mLayout.canScrollHorizontally()) {
        hScroll=event.getAxisValue(MotionEvent.AXIS_HSCROLL);
      }
 else {
        hScroll=0f;
      }
    }
 else     if ((event.getSource() & InputDeviceCompat.SOURCE_ROTARY_ENCODER) != 0) {
      final float axisScroll=event.getAxisValue(MotionEventCompat.AXIS_SCROLL);
      if (mLayout.canScrollVertically()) {
        vScroll=-axisScroll;
        hScroll=0f;
      }
 else       if (mLayout.canScrollHorizontally()) {
        vScroll=0f;
        hScroll=axisScroll;
      }
 else {
        vScroll=0f;
        hScroll=0f;
      }
    }
 else {
      vScroll=0f;
      hScroll=0f;
    }
    if (vScroll != 0 || hScroll != 0) {
      scrollByInternal((int)(hScroll * mScaledHorizontalScrollFactor),(int)(vScroll * mScaledVerticalScrollFactor),event);
    }
  }
  return false;
}
