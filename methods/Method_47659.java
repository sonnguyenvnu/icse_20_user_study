@Override public void onAnimationUpdate(ValueAnimator animation){
  if (!scroller.isFinished()) {
    scroller.computeScrollOffset();
    updateDataOffset();
  }
 else {
    scrollAnimator.cancel();
  }
}
