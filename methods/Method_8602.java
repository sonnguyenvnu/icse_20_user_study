private void checkHeaderVisibility(boolean animated){
  boolean newHintHeader=transformHintToHeader && (isFocused() || getText().length() > 0);
  if (currentDrawHintAsHeader != newHintHeader) {
    if (headerTransformAnimation != null) {
      headerTransformAnimation.cancel();
      headerTransformAnimation=null;
    }
    currentDrawHintAsHeader=newHintHeader;
    if (animated) {
      headerTransformAnimation=new AnimatorSet();
      headerTransformAnimation.playTogether(ObjectAnimator.ofFloat(this,"headerAnimationProgress",newHintHeader ? 1.0f : 0.0f));
      headerTransformAnimation.setDuration(200);
      headerTransformAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
      headerTransformAnimation.start();
    }
 else {
      headerAnimationProgress=newHintHeader ? 1.0f : 0.0f;
    }
    invalidate();
  }
}
