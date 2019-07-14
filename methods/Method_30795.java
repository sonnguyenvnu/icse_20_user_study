protected void pullEdgeEffectBottom(MotionEvent event,float delta){
  mEdgeEffectBottom.onPull(-delta / getHeight(),1f - getMotionEventX(event) / getWidth());
  if (!mEdgeEffectBottom.isFinished()) {
    ViewCompat.postInvalidateOnAnimation(this);
  }
}
